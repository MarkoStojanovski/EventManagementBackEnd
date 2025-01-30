package project3.eventorganizer.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project3.eventorganizer.models.Payment;
import project3.eventorganizer.service.PaymentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllPayments(
            @RequestParam(value = "page", defaultValue = "0") int page
    ) {
        Pageable pageable = PageRequest.of(page, 6);
        Page<Payment> payments = this.paymentService.findAllPayments(pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("payments", payments.getContent());
        response.put("totalPages",payments.getTotalPages());
        response.put("currentPage",payments.getNumber());

        return new ResponseEntity<>(response, HttpStatus.OK);
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
