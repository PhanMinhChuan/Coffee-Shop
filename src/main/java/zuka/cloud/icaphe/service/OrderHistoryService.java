/**
 * FileName : IOrderHistoryService
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service;

import zuka.cloud.icaphe.entity.OrderHistory;

import java.util.List;

/**
 * @author Nguyen Viet Long
 */
public interface OrderHistoryService {

    public OrderHistory getOrderHistoryById(Integer id);

    public List<OrderHistory> getAllOrderHistory();
}
