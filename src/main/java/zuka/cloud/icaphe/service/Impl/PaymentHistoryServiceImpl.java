package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.PaymentHistory;
import zuka.cloud.icaphe.repository.PaymentHistoryRepository;
import zuka.cloud.icaphe.service.PaymentHistoryService;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public void save(PaymentHistory paymentHistory) {
        paymentHistoryRepository.save(paymentHistory);
    }

    @Override
    public void remove(PaymentHistory paymentHistory) {
        // TODO Auto-generated method stub

    }

    public List<PaymentHistory> findAllPaymentHistory() {
        return (List<PaymentHistory>) paymentHistoryRepository.findAll();
    }

    @Override
    public Page findAllPaymentHistory(Integer page, Integer size) {
        return paymentHistoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<PaymentHistory> findById(Long id) {
        // TODO Auto-generated method stub
        return paymentHistoryRepository.findById(id);
    }
}
