package project3.eventorganizer.models.exceptions;

public class TicketSoldOutException extends RuntimeException{
    public TicketSoldOutException() {
        super("No more tickets available for this event!");
    }
}
