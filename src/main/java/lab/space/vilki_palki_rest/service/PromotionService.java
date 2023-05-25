package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Promotion;
import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import java.util.List;

public interface PromotionService {

    PromotionResponse getPromotionById(Long id);
    Promotion getPromotion(Long id);
    List<PromotionResponse> getAllPromotions();

}
