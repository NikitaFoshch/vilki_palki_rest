package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.ProductCategory;
import lab.space.vilki_palki_rest.mapper.ProductCategoryMapper;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki_rest.repository.ProductCategoryRepository;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getProductCategory(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("ProductCategory not found with id " + id));
    }

    @Override
    public ProductCategoryResponse getProductCategoryDTO(Long id) {
        return ProductCategoryMapper.toSimpleDto(getProductCategory(id));
    }

    @Override
    public List<ProductCategoryResponse> getAllProductCategoryOrderByCreateAt() {
        return productCategoryRepository.findAll(Sort.by(Sort.Direction.ASC,"createAt"))
                .stream().map(ProductCategoryMapper::toDto).toList();
    }
}
