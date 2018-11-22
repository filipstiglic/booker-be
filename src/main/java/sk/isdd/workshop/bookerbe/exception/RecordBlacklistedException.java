package sk.isdd.workshop.bookerbe.exception;

/**
 * @Author Filip Stiglic
 */
public class RecordBlacklistedException extends RuntimeException {

	public RecordBlacklistedException(String message) {
		super(message);
	}

	public RecordBlacklistedException(String message, Throwable cause) {
		super(message, cause);
	}

}
