package sk.isdd.workshop.bookerbe.exception.advice;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import sk.isdd.workshop.bookerbe.exception.basic.ApiException;
import sk.isdd.workshop.bookerbe.exception.basic.ErrorCode;

/**
 * @Author Filip Stiglic
 */


@ControllerAdvice
@Order(20)
public class GlobalExceptionHandlerControllerAdvice  {

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleOthersException(Exception ex, WebRequest request) {
		
		ApiException apiError =	new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SYSTEM_ERROR, ex.getMessage(), "Unexpected exception occurred");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}
