package lab.space.vilki_palki_rest.service;

import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import org.springframework.data.domain.Page;

public interface BannerService {
    Banner getBanner(Long id);
    BannerResponse getBannerDto(Long id);
    Page<BannerResponse> getAllBanners();
}
