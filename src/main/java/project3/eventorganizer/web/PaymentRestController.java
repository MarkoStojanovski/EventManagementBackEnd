package project3.eventorganizer.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        List<Payment> payments = this.paymentService.findAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPayment(
            @PathVariable Long id
    ) {
        Payment payment = this.paymentService.findById(id);
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment
    ) {
        Payment newPayment = this.paymentService.createPayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Payment> deletePayment(
            @PathVariable Long id
    ) {
        this.paymentService.softDelete(id);
        return ResponseEntity.ok().build();
    }

}
