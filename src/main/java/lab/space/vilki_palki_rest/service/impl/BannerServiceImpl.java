package lab.space.vilki_palki_rest.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.mapper.BannerMapper;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import lab.space.vilki_palki_rest.repository.BannerRepository;
import lab.space.vilki_palki_rest.service.BannerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public Banner getBanner(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Banner not found by id " + id));
    }

    @Override
    public BannerResponse getBannerDto(Long id) {
        return BannerMapper.toDto(getBanner(id));
    }

    @Override
    public List<BannerResponse> getAllBanners() {
        return bannerRepository.findAll(Sort.by(Sort.Direction.DESC,"createAt"))
                .stream().map(BannerMapper::toDto).toList();
    }
}
