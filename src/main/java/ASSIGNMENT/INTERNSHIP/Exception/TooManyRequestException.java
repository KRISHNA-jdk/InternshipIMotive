package ASSIGNMENT.INTERNSHIP.Exception;

public class TooManyRequestException extends RuntimeException {
    public TooManyRequestException(String message) {
        super(message);
    }
}
