package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class StructureMapper {
    private final StructureCategoryService structureCategoryService;

    public StructureResponse toDto(Structure structure) {
        return StructureResponse.builder()
                .id(structure.getId())
                .name(structure.getName())
                .structureCategory(structureCategoryService.getStructureCategoryDto(structure.getStructureCategory().getId()))
                .weight(structure.getWeight().toString())
                .price(structure.getPrice())
                .image(
                        "http://slj.avada-media-dev1.od.ua/Vilki_Palki_Admin/files/" + structure.getImage()
                )
                .build();
    }

    public StructureResponse toSimpleDto(Structure structure) {
        return StructureResponse.builder()
                .id(structure.getId())
                .name(structure.getName())
                .image(
                        "http://slj.avada-media-dev1.od.ua/Vilki_Palki_Admin/files/" + structure.getImage()
                )
                .build();
    }
}
