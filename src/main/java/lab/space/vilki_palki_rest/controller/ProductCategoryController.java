package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("product-categories")
@AllArgsConstructor
@Tag(name = "Product Categories", description = "Operations related to Product Categories")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @Operation(summary = "Get product category by id", description = "Enter your value")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized"),
            @ApiResponse(responseCode = "404",description = "Not found")
    })
    @GetMapping("get-product-category/{id}")
    public ResponseEntity<?> getProductCategory(@PathVariable Long id) {
        if (id < 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Product Category Id must be >=1");
        }
        try {
            ProductCategoryResponse productCategoryResponse = productCategoryService.getProductCategoryDTO(id);
            return ResponseEntity.ok(productCategoryResponse);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product Category not found with id " + id);
        }
    }

    @Operation(summary = "Get all product categories", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("get-all-product-categories/{page}")
    public ResponseEntity<?> getAllProductCategories(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(productCategoryService.getAllProductCategoryOrderByCreateAt(page));
    }
}
