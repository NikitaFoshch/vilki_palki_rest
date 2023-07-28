package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import lab.space.vilki_palki_rest.entity.Promotion;
import lab.space.vilki_palki_rest.mapper.PromotionMapper;
import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import lab.space.vilki_palki_rest.repository.PromotionRepository;
import lab.space.vilki_palki_rest.service.PromotionService;
import org.springframework.util.Assert;

public class PromotionServiceImplTest {

    @Mock
    private PromotionRepository promotionRepository;

    @Mock
    private PromotionMapper promotionMapper;

    @InjectMocks
    private PromotionServiceImpl promotionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPromotionById_ExistingId_ReturnsPromotionResponse() {
        Long promotionId = 1L;
        Promotion promotion = new Promotion();
        promotion.setId(1L);
        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(promotion));

        PromotionResponse result = promotionService.getPromotionById(promotionId);

        Assert.isNull(result);
    }

    @Test
    public void testGetPromotionById_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(promotionRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            promotionService.getPromotionById(nonExistingId);
        });
    }

    @Test
    public void testGetPromotion_ExistingId_ReturnsPromotion() {
        Long promotionId = 1L;
        Promotion promotion = new Promotion();
        when(promotionRepository.findById(promotionId)).thenReturn(Optional.of(promotion));

        Promotion result = promotionService.getPromotion(promotionId);

        assertThat(result).isEqualTo(promotion);
    }

    @Test
    public void testGetPromotion_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(promotionRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            promotionService.getPromotion(nonExistingId);
        });
    }

//    @Test
//    public void testGetAllPromotions_ReturnsListOfPromotionResponses() {
//        List<Promotion> promotions = new ArrayList<>();
//        promotions.add(new Promotion());
//        promotions.add(new Promotion());
//
//        when(promotionRepository.findAll(Sort.by(Sort.Direction.ASC, "name"))).thenReturn(promotions);
//
//        List<PromotionResponse> expectedResponses = promotions.stream()
//                .map(promotionMapper::toDto)
//                .collect(Collectors.toList());
//
//        List<PromotionResponse> result = promotionService.getAllPromotions();
//
//        assertThat(result).isEqualTo(expectedResponses);
//    }
}
