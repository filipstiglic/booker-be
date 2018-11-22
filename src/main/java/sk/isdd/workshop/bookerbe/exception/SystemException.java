package sk.isdd.workshop.bookerbe.exception;

/**
 * Created by Denis StiGo Stiglic on 21. 3. 2018.
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
