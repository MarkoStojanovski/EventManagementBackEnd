package project3.eventorganizer.models.exceptions;

public class InvalidUserCredentials extends RuntimeException{
    public InvalidUserCredentials() {
        super("Invalid User Credentials !");
    }
}
