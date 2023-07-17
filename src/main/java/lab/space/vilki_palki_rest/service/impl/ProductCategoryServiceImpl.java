package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.ProductCategory;
import lab.space.vilki_palki_rest.mapper.ProductCategoryMapper;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki_rest.repository.ProductCategoryRepository;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final  int DEFAULT_PAGE_SIZE = 10;

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
    public Page<ProductCategoryResponse> getAllProductCategoryOrderByCreateAt(int page) {
        return productCategoryRepository.findAll(PageRequest.of(page,DEFAULT_PAGE_SIZE))
                .map(ProductCategoryMapper::toDto);
    }
}
