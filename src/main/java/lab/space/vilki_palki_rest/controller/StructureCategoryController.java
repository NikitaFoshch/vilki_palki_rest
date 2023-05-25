package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("structure-categories")
@AllArgsConstructor
public class StructureCategoryController {
    private final StructureCategoryService structureCategoryService;

    @GetMapping("get-all-structure-categories")
    public ResponseEntity<List<StructureCategoryResponse>> getAllStructureCategories(){
        return ResponseEntity.ok(structureCategoryService.getAllStructureCategories());
    }
}
