/**
 * FileName : OrderRepository
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import zuka.cloud.icaphe.entity.Order;

/**
 * @author Nguyen Viet Long
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page findAllById(Integer id, Pageable pageable);

    @Query(value = "SELECT * FROM order WHERE id =?1 AND id_shop =?2", nativeQuery = true)
    Order findOneOrder(Integer id, Integer idShop);
}