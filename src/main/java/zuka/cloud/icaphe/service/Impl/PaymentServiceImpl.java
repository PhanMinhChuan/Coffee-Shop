package zuka.cloud.icaphe.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.entity.Payment;
import zuka.cloud.icaphe.repository.PaymentRepository;
import zuka.cloud.icaphe.service.PaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public void remove(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public List<Menu> findAllPaymentByIdShop(Integer idShop, Integer page, Integer size) {
        return paymentRepository.findPaymentById(idShop, PageRequest.of(page, size));
    }

    public List<Payment> findAllPayment() {
        return (List<Payment>) paymentRepository.findAll();
    }

    @Override
    public Page findAllPayment(Integer page, Integer size) {
        return paymentRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Payment findById(Long id) {
        // TODO Auto-generated method stub
        return paymentRepository.getOne(id);
    }
}
