package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShoppingCartMapper {
    private final ProductService productService;

    public ShoppingCartResponse toDto(ShoppingCart shoppingCart) {
        return ShoppingCartResponse.builder()
                .id(shoppingCart.getId())
                .count(shoppingCart.getCount())
                .userId(shoppingCart.getUser().getId())
                .product(productService.getProductSimpleDto(shoppingCart.getProduct().getId()))
                .build();

    }
}
