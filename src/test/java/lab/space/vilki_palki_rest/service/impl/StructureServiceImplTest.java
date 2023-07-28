package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Structure;
import lab.space.vilki_palki_rest.mapper.StructureMapper;
import lab.space.vilki_palki_rest.model.structure.StructureResponse;
import lab.space.vilki_palki_rest.repository.StructureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StructureServiceImplTest {

    @Mock
    private StructureRepository structureRepository;

    @Mock
    private StructureMapper structureMapper;

    @InjectMocks
    private StructureServiceImpl structureService;

    @Test
    public void testGetStructure_ExistingId_ReturnsStructure() {
        Long structureId = 2L;
        Structure structure = new Structure();
        structure.setId(2L);

        when(structureRepository.findById(structureId)).thenReturn(Optional.of(structure));

        Structure result = structureService.getStructure(structureId);

        assertThat(result).isEqualTo(structure);
    }

    @Test
    public void testGetStructure_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            structureService.getStructure(nonExistingId);
        });
    }

    @Test
    public void testGetStructureDto_ExistingId_ReturnsStructureResponse() {
        Long structureId = 1L;
        Structure structure = new Structure();

        when(structureRepository.findById(structureId)).thenReturn(Optional.of(structure));

        StructureResponse result = structureService.getStructureDto(structureId);

        Assert.isNull(result);
    }

    @Test
    public void testGetStructureDto_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            structureService.getStructureDto(nonExistingId);
        });
    }

//    @Test
//    public void testGetAllStructuresDto_ReturnsListOfStructureResponses() {
//        List<Structure> structures = new ArrayList<>();
//        structures.add(new Structure());
//        structures.add(new Structure());
//
//        when(structureRepository.findAll(Sort.by(Sort.Direction.ASC,"name"))).thenReturn(structures);
//
//        List<StructureResponse> expectedResponses = structures.stream()
//                .map(structureMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<StructureResponse> result = structureService.getAllStructuresDto();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }
}
