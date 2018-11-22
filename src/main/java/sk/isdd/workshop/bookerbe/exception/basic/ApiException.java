package sk.isdd.workshop.bookerbe.exception.basic;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;

/**
 * @Author Filip Stiglic
 */
public class ApiException implements Serializable {

	private HttpStatus status;
	private ErrorCode errorCode;
	private String message;
	private List<String> errors;

	public ApiException() {
	}

	public ApiException(HttpStatus status, ErrorCode errorCode, String message, List<String> errors) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		this.errors = errors;
	}

	public ApiException(HttpStatus status, ErrorCode errorCode, String message, String error) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.message = message;
		errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}

