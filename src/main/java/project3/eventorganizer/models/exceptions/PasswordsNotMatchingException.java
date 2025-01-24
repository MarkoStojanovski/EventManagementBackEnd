package project3.eventorganizer.models.exceptions;

public class PasswordsNotMatchingException extends RuntimeException{
    public PasswordsNotMatchingException() {
        super("Password and RepeatedPassword are not matching !");
    }
}
