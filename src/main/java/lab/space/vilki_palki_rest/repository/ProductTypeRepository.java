package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Hidden
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long>, JpaSpecificationExecutor<ProductType> {
    boolean existsByName(String name);
}
