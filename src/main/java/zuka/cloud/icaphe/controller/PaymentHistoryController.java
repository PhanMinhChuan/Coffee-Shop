package zuka.cloud.icaphe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import zuka.cloud.icaphe.entity.PaymentHistory;
import zuka.cloud.icaphe.service.PaymentHistoryService;

import java.util.List;

@RestController
@RequestMapping("payment-history")
public class PaymentHistoryController {
    @Autowired
    private PaymentHistoryService phs;

    @GetMapping(value = "/all", params = { "page", "size" })
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public ResponseEntity<List<PaymentHistory>> findAllTables(@RequestParam("page") int page, @RequestParam("size") int size) {
        Page ph = phs.findAllPaymentHistory(page, size);
        return new ResponseEntity<>(ph.getContent(), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseBody
    @PreAuthorize("hasRole('SHOP') and hasRole('STAFF')")
    public ResponseEntity<PaymentHistory> createPaymentHistory(@RequestBody PaymentHistory paymentHistory) {
        phs.save(paymentHistory);
        return new ResponseEntity<>(paymentHistory, HttpStatus.CREATED);
    }
}
