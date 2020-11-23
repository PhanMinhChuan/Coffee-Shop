package zuka.cloud.icaphe.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.entity.Payment;

import java.util.List;

@Service
public interface PaymentService {

    Payment p = new Payment();

    public Page findAllPayment(Integer page, Integer size);

    public Payment findById(Long id);

    public void save(Payment payment);

    public void remove(Payment payment);

    public List<Menu> findAllPaymentByIdShop(Integer idShop, Integer page, Integer size);
}
