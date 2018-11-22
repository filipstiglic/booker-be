package sk.isdd.workshop.bookerbe.exception;

import java.util.List;

/**
 * @Author Filip Stiglic
 */
public class MessageContextValidationException extends RuntimeException {

	private List<String> validationErrors;

	public MessageContextValidationException(String message, List<String> validationErrors) {
		super(message);
		this.validationErrors = validationErrors;
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}
}
