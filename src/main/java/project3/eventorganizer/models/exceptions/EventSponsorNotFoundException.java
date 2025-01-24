package project3.eventorganizer.models.exceptions;

public class EventSponsorNotFoundException extends RuntimeException {
    public EventSponsorNotFoundException() {
        super("Event with the Sponsor Not Found !");
    }
}
