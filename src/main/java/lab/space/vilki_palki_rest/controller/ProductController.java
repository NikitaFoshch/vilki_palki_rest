package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lab.space.vilki_palki_rest.service.ProductService;
import lab.space.vilki_palki_rest.util.ErrorMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Tag(name = "Products", description = "Operations related to Products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products by request",
            description = "Enter your value, producTypeId can be 0." +
                    "\n If you enter 0 , then get all products without filtering by type")
    @GetMapping("get-all-products/{productTypeId}/{productCategoryId}")
    public ResponseEntity<?> getAllProductsByRequest(@Valid @PathVariable Long productTypeId, @PathVariable Long productCategoryId,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        try {
            List<ProductResponse> productResponse = productService.getAllProduct(productTypeId, productCategoryId);
            return ResponseEntity.ok(productResponse);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Products not found");
        }
    }

    @Operation(summary = "Get product by id", description = "Enter your value")
    @GetMapping("get-product/{id}")
    public ResponseEntity<?> getProduct(@Min(1) @PathVariable Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorMapper.mapErrors(bindingResult));
        }
        try {
            ProductResponse productResponse = productService.getProductDto(id);
            return ResponseEntity.ok(productResponse);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with id " + id);
        }
    }
}
