package lab.space.vilki_palki_rest.repository;

import lab.space.vilki_palki_rest.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
    List<Address> findAllByUserIdOrderByCreateAt(Long id);
}
