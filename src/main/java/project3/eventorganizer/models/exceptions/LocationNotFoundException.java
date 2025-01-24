package project3.eventorganizer.models.exceptions;

public class LocationNotFoundException extends RuntimeException{
    public LocationNotFoundException() {
        super("Location Not Found !");
    }
}
