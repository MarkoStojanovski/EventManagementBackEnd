package project3.eventorganizer.models.exceptions;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException() {
        super("Feedback Not Found !");
    }
}
