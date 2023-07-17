package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.ProductType;
import lab.space.vilki_palki_rest.mapper.ProductTypeMapper;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki_rest.repository.ProductTypeRepository;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
    private final ProductTypeRepository productTypeRepository;


    @Override
    public ProductType getProductType(Long id) {
        return productTypeRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("ProductType not found with id " + id));
    }

    @Override
    public ProductTypeResponse getProductTypeDto(Long id) {
        return ProductTypeMapper.toDto(getProductType(id));
    }

    @Override
    public Page<ProductTypeResponse> getAllProductType() {
        return productTypeRepository.findAll(PageRequest.of(1,10)).map(ProductTypeMapper::toDto);
    }
}
