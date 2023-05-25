package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;

public interface StructureCategoryMapper {
    static StructureCategoryResponse toDto(StructureCategory structureCategory) {
        return StructureCategoryResponse.builder()
                .id(structureCategory.getId())
                .name(structureCategory.getName())
                .build();
    }
}
