package lab.space.vilki_palki_rest.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lab.space.vilki_palki_rest.service.impl.BannerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.mapper.BannerMapper;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import lab.space.vilki_palki_rest.repository.BannerRepository;

public class BannerServiceImplTest {

    @Mock
    private BannerRepository bannerRepository;

    @InjectMocks
    private BannerServiceImpl bannerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetBanner_ExistingId_ReturnsBanner() {
        Long bannerId = 1L;
        Banner banner = new Banner();
        when(bannerRepository.findById(bannerId)).thenReturn(Optional.of(banner));

        Banner result = bannerService.getBanner(bannerId);

        assertThat(result).isEqualTo(banner);
    }

    @Test
    public void testGetBanner_NonExistingId_ThrowsEntityNotFoundException() {
        Long nonExistingId = 2L;
        when(bannerRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(EntityNotFoundException.class, () -> {
            bannerService.getBanner(nonExistingId);
        });
    }

    @Test
    public void testGetBannerDto_ExistingId_ReturnsBannerResponse() {
        Long bannerId = 1L;
        Banner banner = new Banner();
        when(bannerRepository.findById(bannerId)).thenReturn(Optional.of(banner));
        BannerResponse expectedResponse = BannerMapper.toDto(banner);

        BannerResponse result = bannerService.getBannerDto(bannerId);

        assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    public void testGetAllBanners_ReturnsListOfBannerResponses() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner());
        banners.add(new Banner());
        when(bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"))).thenReturn(banners);
        List<BannerResponse> expectedResponses = banners.stream()
                .map(BannerMapper::toDto)
                .collect(Collectors.toList());

        List<BannerResponse> result = bannerService.getAllBanners();

        assertThat(result).isEqualTo(expectedResponses);
    }
}
