package learn.spring.exception;

/**
 * Created by artem_shevtsov on 1/19/17.
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
