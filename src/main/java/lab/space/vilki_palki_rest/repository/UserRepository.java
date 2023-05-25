package lab.space.vilki_palki_rest.repository;

import io.swagger.v3.oas.annotations.Hidden;
import lab.space.vilki_palki_rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Hidden
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.password = null where u.email like :email")
    void removePasswordByUserEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.password = :password where u.email like :email")
    void addPasswordByUserEmail(String email, String password);
}
