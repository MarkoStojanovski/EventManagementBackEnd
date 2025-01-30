package project3.eventorganizer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project3.eventorganizer.models.Payment;

import java.util.List;

public interface PaymentService {

    Payment findById(Long id);

    Page<Payment> findAllPayments(Pageable pageable);

    Payment createPayment(Payment payment);

    void softDelete(Long id);

}
