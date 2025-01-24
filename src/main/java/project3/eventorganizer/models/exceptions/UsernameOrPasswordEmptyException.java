package project3.eventorganizer.models.exceptions;

public class UsernameOrPasswordEmptyException extends RuntimeException{
    public UsernameOrPasswordEmptyException() {
        super("Username/Password empty");
    }
}
