package learn.spring.exception;

public class SessionNotFoundException extends Exception {

    public SessionNotFoundException(String message) {
        super(message);
    }
}
