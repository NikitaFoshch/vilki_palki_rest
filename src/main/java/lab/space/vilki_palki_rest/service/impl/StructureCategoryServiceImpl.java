package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.mapper.StructureCategoryMapper;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki_rest.repository.StructureCategoryRepository;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StructureCategoryServiceImpl implements StructureCategoryService {
    private final StructureCategoryRepository structureCategoryRepository;

    @Override
    public StructureCategory getStructureCategory(Long id) {
        return structureCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StructureCategory not found by id " + id));
    }

    @Override
    public StructureCategoryResponse getStructureCategoryDto(Long id) {
        return StructureCategoryMapper.toDto(getStructureCategory(id));
    }

    @Override
    public List<StructureCategoryResponse> getAllStructureCategories() {
        return structureCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(StructureCategoryMapper::toDto).collect(Collectors.toList());
    }
}
