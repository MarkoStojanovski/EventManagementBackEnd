package project3.eventorganizer.models.exceptions;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException() {
        super("Ticket Not Found !");
    }
}
