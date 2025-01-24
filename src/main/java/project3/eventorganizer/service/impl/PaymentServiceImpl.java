package project3.eventorganizer.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.models.exceptions.PaymentNotFoundException;
import project3.eventorganizer.repository.PaymentRepository;
import project3.eventorganizer.service.PaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Payment createPayment(Payment payment) {
        Payment newPayment = new Payment(payment.getPaymentDate());
        return this.paymentRepository.save(newPayment);
    }

    @Override
    public Payment findById(Long id) {
        return this.paymentRepository.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
    }

    @Override
    public List<Payment> findAll() {
        return this.paymentRepository.findAll();
    }

    @Override
    public void softDelete(Long id) {
        Payment payment = this.paymentRepository.findById(id)
                .orElseThrow(PaymentNotFoundException::new);
        payment.setDeleted(true);
        this.paymentRepository.save(payment);
    }
}
