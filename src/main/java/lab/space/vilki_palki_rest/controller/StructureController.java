package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("structures")
@AllArgsConstructor
public class StructureController {
    private final StructureService structureService;

    @GetMapping("get-all-structures")
    public ResponseEntity<List<StructureResponse>> getAllStructure(){
        return ResponseEntity.ok(structureService.getAllStructuresDto());
    }
}
