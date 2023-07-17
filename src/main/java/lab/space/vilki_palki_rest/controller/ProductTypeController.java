package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product-type")
@AllArgsConstructor
@Tag(name = "Product Types", description = "Operations related to Product Types")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @Operation(summary = "Get all product types")
    @GetMapping("get-all-product-types")
    public ResponseEntity<Page<ProductTypeResponse>> getAllProductType() {
        return ResponseEntity.ok(productTypeService.getAllProductType());
    }
}
