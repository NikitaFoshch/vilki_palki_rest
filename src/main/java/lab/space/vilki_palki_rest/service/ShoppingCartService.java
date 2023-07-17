package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ShoppingCartService {
    ShoppingCart getShoppingCart(Long id);
    Page<ShoppingCartResponse> getAllShoppingCartByUser(int page);
    ResponseEntity<?> deleteShoppingCart(Long id);
    ResponseEntity<?> saveShoppingCart(ShoppingCartSaveRequest shoppingCartSaveRequest);
}
