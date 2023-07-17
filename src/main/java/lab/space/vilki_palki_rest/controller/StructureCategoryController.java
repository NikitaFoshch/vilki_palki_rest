package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("structure-categories")
@AllArgsConstructor
@Tag(name = "Structure Categories", description = "Operations related to Structure Categories")
public class StructureCategoryController {
    private final StructureCategoryService structureCategoryService;

    @Operation(summary = "Get all structure categories")
    @GetMapping("get-all-structure-categories")
    public ResponseEntity<Page<StructureCategoryResponse>> getAllStructureCategories() {
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategories());
    }
}
