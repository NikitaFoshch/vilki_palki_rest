package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.Structure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface StructureRepository extends JpaRepository<Structure, Long>, JpaSpecificationExecutor<Structure> {
    boolean existsByName(String name);

}
