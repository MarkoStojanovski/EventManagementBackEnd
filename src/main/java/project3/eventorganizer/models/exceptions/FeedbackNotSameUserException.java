package project3.eventorganizer.models.exceptions;

public class FeedbackNotSameUserException extends RuntimeException{
    public FeedbackNotSameUserException() {
        super("Feedback can be updated only by same User that gave it !");
    }
}
