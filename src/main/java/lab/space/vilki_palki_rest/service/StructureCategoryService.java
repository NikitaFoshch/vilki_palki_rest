package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import org.springframework.data.domain.Page;

public interface StructureCategoryService {
    StructureCategory getStructureCategory(Long id);
    StructureCategoryResponse getStructureCategoryDto(Long id);
    Page<StructureCategoryResponse> getAllStructureCategories();
}
