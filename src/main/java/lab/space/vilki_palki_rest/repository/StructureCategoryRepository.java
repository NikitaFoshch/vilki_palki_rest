package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.StructureCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface StructureCategoryRepository extends JpaRepository<StructureCategory, Long>, JpaSpecificationExecutor<StructureCategory> {
    boolean existsByName(String name);
}
