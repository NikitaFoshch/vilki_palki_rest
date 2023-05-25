package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;

import java.util.List;

public interface BannerService {
    Banner getBanner(Long id);
    BannerResponse getBannerDto(Long id);
    List<BannerResponse> getAllBanners();
}
