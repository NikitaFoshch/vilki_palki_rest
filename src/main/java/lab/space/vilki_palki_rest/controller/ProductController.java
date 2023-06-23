package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.product.ProductRequest;
import lab.space.vilki_palki_rest.model.product.ProductResponse;
import lab.space.vilki_palki_rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Tag(name = "Products", description = "Operations related to Products")
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Get all products by request" ,
            description = "Enter your value, producTypeId can be null." +
            "\n If you enter null , then get all products without filtering by type")
    @GetMapping("get-all-products")
    public ResponseEntity<List<ProductResponse>> getAllProductsByRequest(@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.getAllProduct(request));
    }

    @Operation(summary = "Get product by id" , description = "Enter your value")
    @GetMapping("get-product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductDto(id));
    }
}
