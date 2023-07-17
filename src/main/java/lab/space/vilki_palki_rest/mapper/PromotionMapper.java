package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Promotion;
import lab.space.vilki_palki_rest.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@AllArgsConstructor
public class PromotionMapper {
    private final ProductService productService;

    public PromotionResponse toSimpleDto(Promotion promotion) {
        return PromotionResponse.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .image(
                        UriComponentsBuilder
                                .fromUri(ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri())
                                .path("/files/" + promotion.getImage()).toUriString()
                )
                .build();
    }

    public PromotionResponse toDto(Promotion promotion) {
        return PromotionResponse.builder()
                .id(promotion.getId())
                .name(promotion.getName())
                .image(
                        "http://slj.avada-media-dev1.od.ua/Vilki_Palki_Api/files/" + promotion.getImage()
                )
                .totalPrice(promotion.getTotalPrice().longValue())
                .product(productService.getProductSimpleDto(promotion.getProduct().getId()))
                .build();
    }

}
