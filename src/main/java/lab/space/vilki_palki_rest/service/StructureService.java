package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import org.springframework.data.domain.Page;

public interface StructureService {
    Structure getStructure(Long id);
    StructureResponse getStructureDto(Long id);
    Page<StructureResponse> getAllStructuresDto(Integer page);
}
