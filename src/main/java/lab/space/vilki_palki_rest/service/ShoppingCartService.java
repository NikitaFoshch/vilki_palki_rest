package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Long id);
    List<ShoppingCartResponse> getAllShoppingCartByUserId(Long id);
    void deleteShoppingCart(Long id);
    void saveShoppingCart(ShoppingCartSaveRequest shoppingCartSaveRequest);
}
