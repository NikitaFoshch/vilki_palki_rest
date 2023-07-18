package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.mapper.StructureCategoryMapper;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki_rest.repository.StructureCategoryRepository;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StructureCategoryServiceImpl implements StructureCategoryService {
    private final StructureCategoryRepository structureCategoryRepository;
    private final  int DEFAULT_PAGE_SIZE = 10;

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
    public Page<StructureCategoryResponse> getAllStructureCategories(Integer page) {
        return structureCategoryRepository.findAll(PageRequest.of(page,DEFAULT_PAGE_SIZE)).map(StructureCategoryMapper::toDto);
    }
}
