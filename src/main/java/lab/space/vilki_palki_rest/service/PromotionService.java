package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Promotion;
import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import org.springframework.data.domain.Page;

public interface PromotionService {

    PromotionResponse getPromotionById(Long id);
    Promotion getPromotion(Long id);
    Page<PromotionResponse> getAllPromotions();

}
