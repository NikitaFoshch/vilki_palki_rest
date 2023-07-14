package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import lab.space.vilki_palki_rest.service.PromotionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("promotions")
@AllArgsConstructor
@Tag(name = "Promotions", description = "Operations related to Promotions")
public class PromotionController {
    private final PromotionService promotionService;

    @Operation(summary = "Get all promotions")
    @GetMapping("get-all-promotions")
    public ResponseEntity<List<PromotionResponse>> getAllPromotions() {
        return ResponseEntity.ok(promotionService.getAllPromotions());
    }

    @Operation(summary = "Get promotion by id", description = "Enter your value")
    @GetMapping("get-promotion/{id}")
    public ResponseEntity<PromotionResponse> getPromotion(@PathVariable Long id) {
        return ResponseEntity.ok(promotionService.getPromotionById(id));
    }
}
