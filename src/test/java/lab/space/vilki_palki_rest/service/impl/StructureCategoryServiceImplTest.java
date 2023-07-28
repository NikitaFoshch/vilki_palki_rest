package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.service.impl.StructureCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lab.space.vilki_palki_rest.entity.StructureCategory;
import lab.space.vilki_palki_rest.mapper.StructureCategoryMapper;
import lab.space.vilki_palki_rest.model.structure_category.StructureCategoryResponse;
import lab.space.vilki_palki_rest.repository.StructureCategoryRepository;
import lab.space.vilki_palki_rest.service.StructureCategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

public class StructureCategoryServiceImplTest {

    @Mock
    private StructureCategoryRepository structureCategoryRepository;

    @Mock
    private StructureCategoryMapper structureCategoryMapper;

    @InjectMocks
    private StructureCategoryServiceImpl structureCategoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStructureCategory_ExistingId_ReturnsStructureCategory() {
        Long structureCategoryId = 1L;
        StructureCategory structureCategory = new StructureCategory();
        when(structureCategoryRepository.findById(structureCategoryId)).thenReturn(Optional.of(structureCategory));

        StructureCategory result = structureCategoryService.getStructureCategory(structureCategoryId);

        assertThat(result).isEqualTo(structureCategory);
    }

    @Test
    public void testGetStructureCategory_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(structureCategoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            structureCategoryService.getStructureCategory(nonExistingId);
        });
    }

    @Test
    public void testGetStructureCategoryDto_ExistingId_ReturnsStructureCategoryResponse() {
        Long structureCategoryId = 1L;
        StructureCategory structureCategory = new StructureCategory();

        when(structureCategoryRepository.findById(structureCategoryId)).thenReturn(Optional.of(structureCategory));

        StructureCategoryResponse result = structureCategoryService.getStructureCategoryDto(structureCategoryId);

        Assert.notNull(result);
    }

    @Test
    public void testGetStructureCategoryDto_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(structureCategoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            structureCategoryService.getStructureCategoryDto(nonExistingId);
        });
    }

//    @Test
//    public void testGetAllStructureCategories_ReturnsListOfStructureCategoryResponses() {
//        List<StructureCategory> structureCategories = new ArrayList<>();
//        structureCategories.add(new StructureCategory());
//        structureCategories.add(new StructureCategory());
//
//        when(structureCategoryRepository.findAll(Sort.by(Sort.Direction.ASC, "name")))
//                .thenReturn(structureCategories);
//
//        List<StructureCategoryResponse> expectedResponses = structureCategories.stream()
//                .map(StructureCategoryMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<StructureCategoryResponse> result = structureCategoryService.getAllStructureCategories();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }
}
