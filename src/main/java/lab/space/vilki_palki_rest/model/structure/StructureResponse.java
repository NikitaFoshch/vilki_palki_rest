package lab.space.vilki_palki_rest.model.structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record StructureResponse(
        Long id,
        String name,
        StructureCategoryResponse structureCategory,
        String weight,
        BigDecimal price,
        String image
) {
}
