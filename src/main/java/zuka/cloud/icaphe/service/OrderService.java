/**
 * FileName : IOrderService
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service;

import java.util.List;

import org.springframework.data.domain.Page;
import zuka.cloud.icaphe.entity.Order;

/**
 * @author Nguyen Viet Long
 */
public interface OrderService {

    List<Order> createOrder(Long shopId, Long tableId, List<Long> listItemId);

    List<Order> approvalOrder(List<Long> listOrderId);

    List<Order> rejectOrder(List<Long> listOrderId);

    List<Order> deleteLogicOrder(List<Long> listOrderId);

    List<Order> cancelDeleteLogicOrder(List<Long> listOrderId);

    List<Long> deletePhysicsOrder(List<Long> listOrderId);

    Page getAllOrderAllShop(Integer page, Integer size);

    Page getAllOrder(Integer idShop, Integer page, Integer size);

    Order getOneOrderById(Integer id, Integer idShop);

    Order updateOrder(Order order);
}
