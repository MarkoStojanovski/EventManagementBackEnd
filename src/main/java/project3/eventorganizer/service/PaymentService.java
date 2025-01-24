package project3.eventorganizer.service;

import project3.eventorganizer.models.Payment;

import java.util.List;

public interface PaymentService {

    Payment findById(Long id);

    List<Payment> findAll();

    Payment createPayment(Payment payment);

    void softDelete(Long id);

}
