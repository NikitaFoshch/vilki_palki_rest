package lab.space.vilki_palki_rest.service.impl;

import javax.persistence.EntityNotFoundException;
import lab.space.vilki_palki_rest.entity.Promotion;
import lab.space.vilki_palki_rest.mapper.PromotionMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lab.space.vilki_palki_rest.model.promotion.PromotionResponse;
import lab.space.vilki_palki_rest.repository.PromotionRepository;
import lab.space.vilki_palki_rest.service.PromotionService;

@Service
@Slf4j
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;
    private final  int DEFAULT_PAGE_SIZE = 10;
    @Override
    public PromotionResponse getPromotionById(Long id) {
        return promotionMapper.toDto(getPromotion(id));
    }

    @Override
    public Promotion getPromotion(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Promotion not found with id " + id));
    }

    @Override
    public Page<PromotionResponse> getAllPromotions(Integer page) {
        return promotionRepository.findAll(PageRequest.of(page,DEFAULT_PAGE_SIZE)).map(promotionMapper::toDto);
    }
}
