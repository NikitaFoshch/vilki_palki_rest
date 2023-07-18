package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("structure-categories")
@AllArgsConstructor
@Tag(name = "Structure Categories", description = "Operations related to Structure Categories")
public class StructureCategoryController {
    private final StructureCategoryService structureCategoryService;

    @Operation(summary = "Get all structure categories", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "OK"),
            @ApiResponse(responseCode = "400",description = "Bad Request"),
            @ApiResponse(responseCode = "401",description = "Unauthorized")
    })
    @GetMapping("get-all-structure-categories/{page}")
    public ResponseEntity<?> getAllStructureCategories(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategories(page));
    }
}
