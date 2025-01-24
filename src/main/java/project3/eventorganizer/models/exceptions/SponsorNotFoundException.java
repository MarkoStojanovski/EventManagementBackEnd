package project3.eventorganizer.models.exceptions;

public class SponsorNotFoundException extends RuntimeException {

    public SponsorNotFoundException() {
        super("Sponsor Not Found !");
    }
}
