package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.shopping_cart.ShoppingCartSaveRequest;
import lab.space.vilki_palki_rest.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("shopping-cart")
@AllArgsConstructor
@Tag(name = "Shopping cart", description = "Operations related to Shopping cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get all shopping cart")
    @GetMapping("get-all-shopping-cart")
    public ResponseEntity<?> getAllShoppingCartByUserId() {
        try {
            return ResponseEntity.ok(shoppingCartService.getAllShoppingCartByUserId());
        } catch (
                EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Shopping Carts not found");
        }
    }

    @Operation(summary = "Save shopping cart")
    @PostMapping("save-shopping-cart")
    public ResponseEntity<?> saveShoppingCart(@RequestBody ShoppingCartSaveRequest request) {
        return shoppingCartService.saveShoppingCart(request);
    }

    @Operation(summary = "Delete shopping cart by user by id", description = "Enter your value")
    @DeleteMapping("delete-shopping-cart/{id}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable Long id) {
        return shoppingCartService.deleteShoppingCart(id);
    }
}
