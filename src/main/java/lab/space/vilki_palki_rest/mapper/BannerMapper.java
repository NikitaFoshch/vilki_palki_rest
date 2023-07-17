package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;

public interface BannerMapper {

    static BannerResponse toDto(Banner banner) {
        return BannerResponse.builder()
                .id(banner.getId())
                .name(banner.getName())
                .image("http://slj.avada-media-dev1.od.ua/Vilki_Palki_Admin/files/" + banner.getImage())
                .build();
    }
}
