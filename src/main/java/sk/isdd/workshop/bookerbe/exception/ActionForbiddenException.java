package sk.isdd.workshop.bookerbe.exception;

/**
 * Created by Daniel Beno.
 */
public class ActionForbiddenException extends RuntimeException {
    public ActionForbiddenException(String message) {
        super(message);
    }

    public ActionForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
