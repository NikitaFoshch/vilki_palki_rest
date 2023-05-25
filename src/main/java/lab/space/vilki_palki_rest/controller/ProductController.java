package lab.space.vilki_palki_rest.controller;

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
public class ProductController {
    private final ProductService productService;

    @GetMapping("get-all-products")
    public ResponseEntity<List<ProductResponse>> getAllProductsByRequest(@RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.getAllProduct(request));
    }

    @GetMapping("get-product/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductDto(id));
    }
}
