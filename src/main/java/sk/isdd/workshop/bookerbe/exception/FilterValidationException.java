package sk.isdd.workshop.bookerbe.exception;

import java.util.Collections;
import java.util.List;

/**
 * @Author Filip Stiglic
 */
public class FilterValidationException extends RuntimeException{

	private List<String> validationErrors;

	public FilterValidationException(String message, List<String> validationErrors) {
		super(message);
		this.validationErrors = validationErrors;
	}

	public FilterValidationException(String message, String validationError) {
		super(message);
		this.validationErrors = Collections.singletonList(validationError);
	}

	public List<String> getValidationErrors() {
		return validationErrors;
	}

}
