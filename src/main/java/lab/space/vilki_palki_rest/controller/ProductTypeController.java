package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.product_type.ProductTypeResponse;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product-type")
@AllArgsConstructor
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @GetMapping("get-all-product-types")
    public ResponseEntity<List<ProductTypeResponse>> getAllProductType(){
        return ResponseEntity.ok(productTypeService.getAllProductType());
    }
}
