package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;

import java.util.List;

public interface StructureService {
    Structure getStructure(Long id);
    StructureResponse getStructureDto(Long id);
    List<StructureResponse> getAllStructuresDto();
}
