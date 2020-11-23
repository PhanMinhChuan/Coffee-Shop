/**
 * FileName : OrderServiceImpl
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import zuka.cloud.icaphe.CommonUtil.Status;
import zuka.cloud.icaphe.entity.Order;
import zuka.cloud.icaphe.entity.OrderHistory;
import zuka.cloud.icaphe.repository.OrderHistoryRepository;
import zuka.cloud.icaphe.repository.OrderRepository;
import zuka.cloud.icaphe.service.OrderService;

/**
 * @author Nguyen Viet Long
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    @Override
    public Page getAllOrderAllShop(Integer page, Integer size) {
        return orderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page getAllOrder(Integer idShop, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return orderRepository.findAllById(idShop, pageable);
    }

    @Override
    public Order getOneOrderById(Integer id, Integer idShop) {
        return orderRepository.findOneOrder(id, idShop);
    }

    @Override
    public List<Order> createOrder(Long shopId, Long tableId, List<Long> listItemId) {
        List<Order> orderEntities = new ArrayList<>();
        for (int i = 0; i < listItemId.size(); i++) {
            Order order = new Order();
            order.setIdShop(shopId);
            order.setIdTable(tableId);
            order.setTime(LocalDateTime.now());
            order.setDeleteFlag(Status.STATUS_DELETE_FLAG.FALSE);
            order.setStatus(Status.STATUS_ORDER.SENT);
            order.setIdItem(listItemId.get(i));
            // TODO set userId form session
            // orderEntity.setIdUser();

            saveOrderHistory(orderRepository.save(order));
        }
        return orderEntities;
    }

    @Override
    public Order updateOrder(Order order) {
        if (orderRepository.findById(order.getId()).isPresent()) {
            Order save = orderRepository.save(order);
            saveOrderHistory(order);
            return save;
        }
        return order;
    }

    @Override
    public List<Order> approvalOrder(List<Long> listOrderId) {
        return updateStatusForOrderes(listOrderId, Status.STATUS_ORDER.APPROVAL);
    }

    @Override
    public List<Order> rejectOrder(List<Long> listOrderId) {
        return updateStatusForOrderes(listOrderId, Status.STATUS_ORDER.REJECT);
    }

    @Override
    public List<Order> deleteLogicOrder(List<Long> listOrderId) {
        List<Order> orderEntities = new ArrayList<>();
        for (int i = 0; i < listOrderId.size(); i++) {
            Order order = orderRepository.findById(listOrderId.get(i)).get();
            order.setDeleteFlag(Status.STATUS_DELETE_FLAG.TRUE);
            orderEntities.add(saveOrderHistory(orderRepository.save(order)));
        }
        return orderEntities;
    }

    @Override
    public List<Order> cancelDeleteLogicOrder(List<Long> listOrderId) {
        List<Order> orderEntities = new ArrayList<>();
        for (int i = 0; i < listOrderId.size(); i++) {
            Order order = orderRepository.findById(listOrderId.get(i)).get();
            order.setDeleteFlag(Status.STATUS_DELETE_FLAG.FALSE);
            orderEntities.add(saveOrderHistory(orderRepository.save(order)));
        }
        return orderEntities;
    }

    @Override
    public List<Long> deletePhysicsOrder(List<Long> listOrderId) {
        for (int i = 0; i < listOrderId.size(); i++) {
            orderRepository.deleteById(listOrderId.get(i));
        }
        return listOrderId;
    }

    private List<Order> updateStatusForOrderes(List<Long> listOrderId, Status.STATUS_ORDER status) {
        List<Order> orderEntities = new ArrayList<>();
        for (int i = 0; i < listOrderId.size(); i++) {
            Order order = orderRepository.findById(listOrderId.get(i)).get();
            order.setStatus(status);
            order.setTime(LocalDateTime.now());
            saveOrderHistory(orderRepository.save(order));
        }
        return orderEntities;
    }

    private Order saveOrderHistory(Order order) {
        order.setId(null);
        OrderHistory orderHistory = new OrderHistory();
        BeanUtils.copyProperties(order, orderHistory);
        orderHistoryRepository.save(orderHistory);
        return order;
    }
}
