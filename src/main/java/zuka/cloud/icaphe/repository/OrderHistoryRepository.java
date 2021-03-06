/**
 * FileName : OrderHistoryRepository
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zuka.cloud.icaphe.entity.OrderHistory;

/**
 * @author Nguyen Viet Long
 */
@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
}