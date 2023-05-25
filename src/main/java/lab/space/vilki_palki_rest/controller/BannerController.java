package lab.space.vilki_palki_rest.controller;

import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import lab.space.vilki_palki_rest.service.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("banners")
@AllArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("get-all-banners")
    public ResponseEntity<List<BannerResponse>> getAllBanners(){
        return ResponseEntity.ok(bannerService.getAllBanners());
    }
}
