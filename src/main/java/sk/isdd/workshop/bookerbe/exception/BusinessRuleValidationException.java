package sk.isdd.workshop.bookerbe.exception;

/**
 * @Author Daniel Beno
 */
public class BusinessRuleValidationException extends RuntimeException {

	public BusinessRuleValidationException(String message) {
		super(message);
	}
}
