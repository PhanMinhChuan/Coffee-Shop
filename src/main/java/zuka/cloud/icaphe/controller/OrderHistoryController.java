/**
 * FileName : OrderHistoryController
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zuka.cloud.icaphe.entity.OrderHistory;
import zuka.cloud.icaphe.service.OrderHistoryService;

/**
 * @author Nguyen Viet Long
 */
@RestController
@RequestMapping("order-histories")
public class OrderHistoryController {

    @Autowired
    OrderHistoryService iOrderHistoryService;

    @GetMapping("{id}")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public OrderHistory getOrderHistoryById(@PathVariable(name = "id") Integer id) {
        return iOrderHistoryService.getOrderHistoryById(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public List<OrderHistory> getListOrder() {
        return iOrderHistoryService.getAllOrderHistory();
    }
}
