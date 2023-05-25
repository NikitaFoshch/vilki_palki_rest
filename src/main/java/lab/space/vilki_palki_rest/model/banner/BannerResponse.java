package lab.space.vilki_palki_rest.model.banner;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record BannerResponse(
        Long id,
        String name,
        String image
) {
}
