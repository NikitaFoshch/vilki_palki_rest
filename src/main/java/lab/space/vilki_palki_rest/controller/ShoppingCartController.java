package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Shopping cart", description = "Operations related to Shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get all shopping cart by user by id", description = "Enter your value")
    @GetMapping("get-all-shopping-cart-by-user-id/{id}")
    public ResponseEntity<List<ShoppingCartResponse>> getAllShoppingCartByUserId(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.getAllShoppingCartByUserId(id));
    }

    @Operation(summary = "Save shopping cart")
    @PostMapping("save-shopping-cart")
    public ResponseEntity<?> saveShoppingCart(@RequestBody ShoppingCartSaveRequest request){
        shoppingCartService.saveShoppingCart(request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete shopping cart by user by id", description = "Enter your value")
    @DeleteMapping("delete-shopping-cart/{id}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable Long id){
        shoppingCartService.deleteShoppingCart(id);
        return ResponseEntity.ok().build();
    }
}
