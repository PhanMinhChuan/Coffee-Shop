package zuka.cloud.icaphe.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.PaymentHistory;

import java.util.Optional;

@Service
public interface PaymentHistoryService {

    PaymentHistory ph = new PaymentHistory();

    public Page findAllPaymentHistory(Integer page, Integer size);

    public Optional<PaymentHistory> findById(Long id);

    public void save(PaymentHistory paymentHistory);

    public void remove(PaymentHistory paymentHistory);
}
