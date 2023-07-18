package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("structures")
@AllArgsConstructor
@Tag(name = "Structures", description = "Operations related to Structures")
public class StructureController {
    private final StructureService structureService;

    @Operation(summary = "Get all structure", description = "This controller returns a total of 10 objects " +
            "according to pagination (first page = 0)")
    @GetMapping("get-all-structures/{page}")
    public ResponseEntity<?> getAllStructure(@PathVariable Integer page) {
        if (page < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Page must be >=0");
        }
        return ResponseEntity.ok(structureService.getAllStructuresDto(page));
    }
}
