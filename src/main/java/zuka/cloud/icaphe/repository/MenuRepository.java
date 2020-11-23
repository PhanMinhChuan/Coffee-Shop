package zuka.cloud.icaphe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zuka.cloud.icaphe.entity.Menu;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByIdShop(long idShop, Pageable pageable);

    //executeQuery()
    @Query(value = "SELECT * FROM menu WHERE id_shop =?1 AND id =?2", nativeQuery = true)
    Optional<Menu> findOne(long idShop, long id);

    //executeUpdate()
    @Modifying
    @Transactional
    @Query(value = "UPDATE menu SET deleted = true WHERE id_shop = :idShop AND id = :id", nativeQuery = true)
    void deleteMenu(@Param("idShop") long id_shop, @Param("id") long id);
}
