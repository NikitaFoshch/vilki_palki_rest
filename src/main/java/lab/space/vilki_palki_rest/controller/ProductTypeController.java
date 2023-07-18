package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.service.ProductTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product-type")
@AllArgsConstructor
@Tag(name = "Product Types", description = "Operations related to Product Types")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    @Operation(summary = "Get all product types", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("get-all-product-types/{page}")
    public ResponseEntity<?> getAllProductType(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(productTypeService.getAllProductType(page));
    }
}
