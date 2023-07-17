package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("structures")
@AllArgsConstructor
@Tag(name = "Structures", description = "Operations related to Structures")
public class StructureController {
    private final StructureService structureService;

    @Operation(summary = "Get all structure")
    @GetMapping("get-all-structures")
    public ResponseEntity<Page<StructureResponse>> getAllStructure() {
        return ResponseEntity.ok(structureService.getAllStructuresDto());
    }
}
