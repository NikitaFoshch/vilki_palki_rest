package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.mapper.StructureMapper;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import lab.space.vilki_palki_rest.repository.StructureRepository;
import lab.space.vilki_palki_rest.service.StructureService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
@Slf4j
@AllArgsConstructor
public class StructureServiceImpl implements StructureService {
    private final StructureRepository structureRepository;
    private final StructureMapper structureMapper;
    private final int DEFAULT_PAGE_SIZE = 10;

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
    public Page<StructureResponse> getAllStructuresDto(Integer page) {
        return structureRepository.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE)).map(structureMapper::toDto);
    }
}
