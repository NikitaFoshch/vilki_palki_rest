package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Product;
import lab.space.vilki_palki_rest.model.product.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct(Long pTId, Long pCId, int page);

    Product getProduct(Long id);

    ProductResponse getProductDto(Long id);

    ProductResponse getProductSimpleDto(Long id);
}
