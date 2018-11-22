package sk.isdd.workshop.bookerbe.exception;

/**
 * Created by Denis StiGo Stiglic on 6. 4. 2018.
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
