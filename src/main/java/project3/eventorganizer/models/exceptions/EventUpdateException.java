package project3.eventorganizer.models.exceptions;

public class EventUpdateException extends RuntimeException{
    public EventUpdateException() {
        super("Event can be updated by only It's own Organizer or the Admin himself !");
    }
}
