package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.service.impl.ProductTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import lab.space.vilki_palki_rest.entity.ProductType;
import lab.space.vilki_palki_rest.mapper.ProductTypeMapper;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki_rest.repository.ProductTypeRepository;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import org.springframework.util.Assert;

public class ProductTypeServiceImplTest {

    @Mock
    private ProductTypeRepository productTypeRepository;

    @InjectMocks
    private ProductTypeServiceImpl productTypeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductType_ExistingId_ReturnsProductType() {
        Long productTypeId = 1L;
        ProductType productType = new ProductType();
        when(productTypeRepository.findById(productTypeId)).thenReturn(Optional.of(productType));

        ProductType result = productTypeService.getProductType(productTypeId);

        assertThat(result).isEqualTo(productType);
    }

    @Test
    public void testGetProductType_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(productTypeRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            productTypeService.getProductType(nonExistingId);
        });
    }

    @Test
    public void testGetProductTypeDto_ExistingId_ReturnsProductTypeResponse() {
        Long productTypeId = 1L;
        ProductType productType = new ProductType();
        when(productTypeRepository.findById(productTypeId)).thenReturn(Optional.of(productType));


        ProductTypeResponse result = productTypeService.getProductTypeDto(productTypeId);

        Assert.notNull(result);
    }

//    @Test
//    public void testGetAllProductType_ReturnsListOfProductTypeResponses() {
//        List<ProductType> productTypes = new ArrayList<>();
//        productTypes.add(new ProductType());
//        productTypes.add(new ProductType());
//
//        when(productTypeRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(productTypes);
//
//        List<ProductTypeResponse> expectedResponses = productTypes.stream()
//                .map(ProductTypeMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<ProductTypeResponse> result = productTypeService.getAllProductType();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }
}
