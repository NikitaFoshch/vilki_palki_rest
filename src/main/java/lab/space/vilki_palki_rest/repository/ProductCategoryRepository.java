package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long>, JpaSpecificationExecutor<ProductCategory> {
    boolean existsByName(String name);
}
