package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;

import java.util.List;

public interface StructureCategoryService {
    StructureCategory getStructureCategory(Long id);
    StructureCategoryResponse getStructureCategoryDto(Long id);
    List<StructureCategoryResponse> getAllStructureCategories();
}
