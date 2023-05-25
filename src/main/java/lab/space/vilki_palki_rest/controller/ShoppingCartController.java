package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartResponse;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import lab.space.vilki_palki_rest.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shopping-cart")
@AllArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("get-all-shopping-cart-by-user-id/{id}")
    public ResponseEntity<List<ShoppingCartResponse>> getAllShoppingCartByUserId(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.getAllShoppingCartByUserId(id));
    }

    @PostMapping("save-shopping-cart")
    public ResponseEntity<?> saveShoppingCart(@RequestBody ShoppingCartSaveRequest request){
        shoppingCartService.saveShoppingCart(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete-shopping-cart/{id}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable Long id){
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.ok().build();
    }
}
