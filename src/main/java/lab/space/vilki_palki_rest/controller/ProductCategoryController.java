package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.product_category.ProductCategoryResponse;
import lab.space.vilki_palki_rest.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product-categories")
@AllArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @GetMapping("get-product-category/{id}")
    public ResponseEntity<ProductCategoryResponse> getProductCategory(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.getProductCategoryDTO(id));
    }

    @GetMapping("get-all-product-categories")
    public ResponseEntity<List<ProductCategoryResponse>> getAllProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllProductCategoryOrderByCreateAt());
    }
}
