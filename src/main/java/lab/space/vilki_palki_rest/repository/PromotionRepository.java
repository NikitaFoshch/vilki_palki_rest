package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    boolean existsByName(String name);
}
