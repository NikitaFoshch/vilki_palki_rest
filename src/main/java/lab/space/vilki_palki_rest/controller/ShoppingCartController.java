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

    @Operation(summary = "Get shopping cart", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @GetMapping("get-shopping-cart/{page}")
    public ResponseEntity<?> getAllShoppingCartByUserId(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        try {
            return ResponseEntity.ok(shoppingCartService.getAllShoppingCartByUser(page));
        } catch (
                EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Shopping Carts not found");
        }
    }

    @Operation(summary = "Add to shopping cart")
    @PostMapping("add-to-shopping-cart")
    public ResponseEntity<?> saveShoppingCart(@RequestBody ShoppingCartSaveRequest request) {
        return shoppingCartService.saveShoppingCart(request);
    }

    @Operation(summary = "Delete product from shopping cart by user by id", description = "Enter your value")
    @DeleteMapping("delete-product-from-shopping-cart/{id}")
    public ResponseEntity<?> deleteShoppingCart(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Shopping Cart Id must be >=1");
        }
        return shoppingCartService.deleteShoppingCart(id);
    }
}
