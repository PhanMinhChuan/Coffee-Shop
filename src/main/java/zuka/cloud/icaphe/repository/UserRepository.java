package zuka.cloud.icaphe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zuka.cloud.icaphe.entity.User;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findById(long idShop);

    //executeUpdate()
    @Modifying
    @Transactional
    @Query(value = "UPDATE menu SET deleted = true WHERE id = :id", nativeQuery = true)
    void deleteUser(@Param("id") long id);
}
