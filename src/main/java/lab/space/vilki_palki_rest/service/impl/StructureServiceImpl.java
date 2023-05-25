package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.mapper.StructureMapper;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import lab.space.vilki_palki_rest.repository.StructureRepository;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class StructureServiceImpl implements StructureService {
    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;

    @Override
    public Structure getStructure(Long id) {
        return structureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Structure not found by id " + id));
    }

    @Override
    public StructureResponse getStructureDto(Long id) {
        return structureMapper.toSimpleDto(getStructure(id));
    }

    @Override
    public List<StructureResponse> getAllStructuresDto() {
        return structureRepository.findAll(Sort.by(Sort.Direction.ASC,"name"))
                .stream().map(structureMapper::toDto).toList();
    }
}
