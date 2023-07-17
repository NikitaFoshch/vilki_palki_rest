package lab.space.vilki_palki_rest.repository;

import lab.space.vilki_palki_rest.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long>, JpaSpecificationExecutor<ShoppingCart> {
}
