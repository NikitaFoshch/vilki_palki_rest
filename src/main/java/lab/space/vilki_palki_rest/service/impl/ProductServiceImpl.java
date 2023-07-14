package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Product;
import lab.space.vilki_palki_rest.mapper.ProductMapper;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lab.space.vilki_palki_rest.repository.ProductRepository;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lab.space.vilki_palki_rest.service.ProductService;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductCategoryService productCategoryService;
    private final ProductTypeService productTypeService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> getAllProduct(Long pTId, Long pCId) {
        return productRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream()
                .filter(product -> pTId == -1
                        ||
                        product.getProductType().equals(
                                productTypeService.getProductType(pTId)
                        )
                )
                .filter(product -> product.getProductCategory().equals(
                                productCategoryService.getProductCategory(pCId)
                        )
                )
                .map(productMapper::toSimpleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product npt found with id " + id));
    }

    @Override
    public ProductResponse getProductDto(Long id) {
        return productMapper.toDto(getProduct(id));
    }

    @Override
    public ProductResponse getProductSimpleDto(Long id) {
        return productMapper.toSimpleDto(getProduct(id));
    }
}
