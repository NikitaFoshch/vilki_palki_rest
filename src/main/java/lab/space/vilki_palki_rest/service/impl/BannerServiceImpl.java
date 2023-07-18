package lab.space.vilki_palki_rest.service.impl;

import lab.space.vilki_palki_rest.entity.Banner;
import lab.space.vilki_palki_rest.mapper.BannerMapper;
import lab.space.vilki_palki_rest.model.banner.BannerResponse;
import lab.space.vilki_palki_rest.repository.BannerRepository;
import lab.space.vilki_palki_rest.service.BannerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
@AllArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final int DEFAULT_PAGE_SIZE = 10;

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
    public Page<BannerResponse> getAllBanners(Integer page) {
        return bannerRepository.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE)).map(BannerMapper::toDto);
    }
}
