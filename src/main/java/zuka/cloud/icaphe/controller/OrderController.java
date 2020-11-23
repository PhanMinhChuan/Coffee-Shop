/**
 * FileName : OrderController
 *
 * @author  Nguyen Viet Long
 * @version 1.0
 * @since   2020-06-09
 */

package zuka.cloud.icaphe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import zuka.cloud.icaphe.CommonUtil.Const;
import zuka.cloud.icaphe.CommonUtil.SubmitId;
import zuka.cloud.icaphe.entity.Order;
import zuka.cloud.icaphe.service.OrderService;

/**
 * @author Nguyen Viet Long
 */
@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    OrderService iOrderService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> getListOrderAllShop(@RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                           @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        Page orders =  iOrderService.getAllOrderAllShop(page, size);
        return new ResponseEntity<>(orders.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "{idShop}")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP')")
    public ResponseEntity<List<Order>> getListOrderOneShop(@PathVariable Integer idShop,
                                                           @RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                           @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        Page orders =  iOrderService.getAllOrder(idShop, page, size);
        return new ResponseEntity<>(orders.getContent(), HttpStatus.OK);
    }

    @GetMapping("{id}/{idShop}")
    public Order getListOrderById(@PathVariable(name = "id") Integer id, @PathVariable(name = "idShop") Integer idShop){
        return iOrderService.getOneOrderById(id, idShop);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP') and hasRole('STAFF') and hasRole('USER')")
    public List<Order> createOrder(@RequestBody SubmitId submitId) {
        return iOrderService.createOrder(submitId.getShopId(), submitId.getTableId(), submitId.getListItemId());
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('ADMIN') and hasRole('SHOP') and hasRole('STAFF') and hasRole('USER')")
    public Order updateOrder(@RequestBody Order order) {
        return iOrderService.updateOrder(order);
    }

    @PatchMapping("approval")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public List<Order> approvalOrder(@RequestBody SubmitId submitId) {
        return iOrderService.approvalOrder(submitId.getListOrderId());
    }

    @PatchMapping("reject")
    @PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
    public List<Order> rejectOrder(@RequestBody SubmitId submitId) {
        return iOrderService.rejectOrder(submitId.getListOrderId());
    }

    @PatchMapping("deleteLogic")
    @PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
    public List<Order> deleteLogicOrder(@RequestBody SubmitId submitId) {
        return iOrderService.deleteLogicOrder(submitId.getListOrderId());
    }

    @PatchMapping("cancelDeleteLogic")
    @PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
    public List<Order> cancelDeleteLogicOrder(@RequestBody SubmitId submitId) {
        return iOrderService.cancelDeleteLogicOrder(submitId.getListOrderId());
    }

    @DeleteMapping("deletePhysics")
    @PreAuthorize("hasRole('ADMIN') and hasRole('STAFF')")
    public List<Long> deletePhysicsOrder(@RequestBody SubmitId submitId) {
        return iOrderService.deletePhysicsOrder(submitId.getListOrderId());
    }
}