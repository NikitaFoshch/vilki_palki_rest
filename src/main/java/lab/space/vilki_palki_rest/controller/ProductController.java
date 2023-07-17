package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lab.space.vilki_palki_rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Tag(name = "Products", description = "Operations related to Products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products by request",
            description = "Enter your value, producTypeId can be 0." +
                    "\n If you enter 0 , then get all products without filtering by type. " +
                    "\nThis controller returns a total of 10 objects according to pagination (first page = 0)")
    @GetMapping("get-all-products/{productTypeId}/{productCategoryId}/{page}")
    public ResponseEntity<?> getAllProductsByRequest(@PathVariable Long productTypeId,
                                                     @PathVariable Long productCategoryId,
                                                     @PathVariable int page
    ) {
        if (productTypeId < 0 && productCategoryId < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product Type Id must be >=0 and Product Category Id must be >=1");
        } else if (productTypeId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product Type Id must be >=0");
        } else if (productCategoryId < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product Category Id must be >=1");
        } else if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        } else {
            try {
                List<ProductResponse> productResponse = productService.getAllProduct(productTypeId, productCategoryId, page);
                return ResponseEntity.ok(productResponse);
            } catch (EntityNotFoundException ex) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Products not found");
            }
        }

    }

    @Operation(summary = "Get product by id", description = "Enter your value")
    @GetMapping("get-product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product Id must be >=1");
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
