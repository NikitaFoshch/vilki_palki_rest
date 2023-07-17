package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Long id);
    List<ShoppingCartResponse> getAllShoppingCartByUserId();
    ResponseEntity<?> deleteShoppingCart(Long id);
    ResponseEntity<?> saveShoppingCart(ShoppingCartSaveRequest shoppingCartSaveRequest);
}
