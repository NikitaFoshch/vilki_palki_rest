package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all structure categories")
    @GetMapping("get-all-structure-categories/{page}")
    public ResponseEntity<?> getAllStructureCategories(@PathVariable int page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategories(page));
    }
}
