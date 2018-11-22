package sk.isdd.workshop.bookerbe.exception;

/**
 * Created by Denis StiGo Stiglic on 21. 3. 2018.
 */
public class AuthorizationException extends RuntimeException {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
