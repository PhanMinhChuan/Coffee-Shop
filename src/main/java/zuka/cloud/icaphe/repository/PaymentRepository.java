package zuka.cloud.icaphe.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zuka.cloud.icaphe.entity.Menu;
import zuka.cloud.icaphe.entity.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Menu> findPaymentById(Integer idShop, PageRequest of);
}
