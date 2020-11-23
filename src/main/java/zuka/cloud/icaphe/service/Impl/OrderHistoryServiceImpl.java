/**
 * FileName : OrderHistoryServiceImpl
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.OrderHistory;
import zuka.cloud.icaphe.repository.OrderHistoryRepository;
import zuka.cloud.icaphe.service.OrderHistoryService;

import java.util.List;

/**
 * @author Nguyen Viet Long
 */
@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

    @Autowired
    OrderHistoryRepository orderHistoryRepository;

    public OrderHistory getOrderHistoryById(Integer id) {
        return orderHistoryRepository.findById(id).get();
    }

    public List<OrderHistory> getAllOrderHistory() {
        return orderHistoryRepository.findAll();
    }
}
