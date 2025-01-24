package project3.eventorganizer.models.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User Not Found !");
    }
}
