package project3.eventorganizer.models.exceptions;

public class EventCreateAttendeeException extends RuntimeException{
    public EventCreateAttendeeException() {
        super("Event creator must be Admin or Organizer !");
    }
}
