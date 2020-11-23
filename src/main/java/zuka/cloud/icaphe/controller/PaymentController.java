package zuka.cloud.icaphe.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.CommonUtil.Const;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.entity.Payment;
import zuka.cloud.icaphe.entity.PaymentHistory;
import zuka.cloud.icaphe.service.PaymentHistoryService;
import zuka.cloud.icaphe.service.PaymentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {
    @Autowired
    private PaymentService ps;

    @Autowired
    private PaymentHistoryService phs;

    @GetMapping(value = "/all", params = { "page", "size" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Payment>> findAllPayment(@RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                        @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        Page payments = ps.findAllPayment(page, size);
        return new ResponseEntity<>(payments.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "{idShop}")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public ResponseEntity<List<Menu>> findAllPaymentByIdShop(@PathVariable Integer idShop,
                                                          @RequestParam(value="page", defaultValue = Const.NUMBER_PAGE_START_DEFAULT) Integer page,
                                                          @RequestParam(value="size", defaultValue = Const.NUMBER_SIZE_PAGE_DEFAULT) Integer size) {
        List<Menu> paymentList = ps.findAllPaymentByIdShop(idShop, page, size);
        return new ResponseEntity<>(paymentList, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF') ")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        payment.setUpdateTime(LocalDate.now());
        ps.save(payment);
        // add payment history
        PaymentHistory ph = new PaymentHistory();
        ph.setOrderId(payment.getOrderId());
        ph.setPaymentStatus(payment.getPaymentStatus());
        ph.setPaymentId(payment.getId());
        phs.save(ph);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public ResponseEntity<Payment> updatePayment(@RequestBody Payment newPayment, @PathVariable Long id) {
        Payment payment = ps.findById(id);
        if (payment != null) {
            ps.save(payment);

            // add payment history
            PaymentHistory ph = new PaymentHistory();
            ph.setOrderId(newPayment.getOrderId());
            ph.setPaymentStatus(newPayment.getPaymentStatus());
            ph.setPaymentId(id);
            phs.save(ph);
        }
        return new ResponseEntity<>(newPayment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF') and hasRole('USER') ")
    public ResponseEntity<Void> cancelPayment(@PathVariable Long id) {
        Payment payment = ps.findById(id);
        if (payment == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        ps.remove(payment);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
