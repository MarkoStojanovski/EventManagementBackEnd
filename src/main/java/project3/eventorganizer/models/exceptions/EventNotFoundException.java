package project3.eventorganizer.models.exceptions;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Event Not Found !");
    }
}
