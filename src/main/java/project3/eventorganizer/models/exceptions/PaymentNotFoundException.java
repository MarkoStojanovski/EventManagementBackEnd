package project3.eventorganizer.models.exceptions;

public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException() {
        super("Payment not Found !");
    }
}
