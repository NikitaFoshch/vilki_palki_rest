package lab.space.vilki_palki_rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import lab.space.vilki_palki_rest.service.BannerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("banners")
@AllArgsConstructor
@Tag(name = "Banners", description = "Operations related to Banners")
public class BannerController {
    private final BannerService bannerService;

    @Operation(summary = "Get all banners")
    @GetMapping("get-all-banners")
    public ResponseEntity<Page<BannerResponse>> getAllBanners() {
        return ResponseEntity.ok(bannerService.getAllBanners());
    }
}
