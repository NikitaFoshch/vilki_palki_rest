package lab.space.vilki_palki_rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import lab.space.vilki_palki_rest.service.PromotionService;
import java.util.List;

@RestController
@RequestMapping("promotions")
@AllArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @GetMapping("get-all-promotions")
    public ResponseEntity<List<PromotionResponse>> getAllPromotions(){
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @GetMapping("get-promotion/{id}")
    public ResponseEntity<PromotionResponse> getPromotion(@PathVariable Long id){
        return ResponseEntity.ok(promotionService.getPromotionById(id));
    }
}
