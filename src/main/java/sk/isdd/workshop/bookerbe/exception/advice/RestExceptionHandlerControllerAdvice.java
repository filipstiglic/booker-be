package sk.isdd.workshop.bookerbe.exception.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sk.isdd.workshop.bookerbe.exception.ActionForbiddenException;
import sk.isdd.workshop.bookerbe.exception.AgreementExistsException;
import sk.isdd.workshop.bookerbe.exception.AgreementSpecificationNotFoundException;
import sk.isdd.workshop.bookerbe.exception.AuthorizationException;
import sk.isdd.workshop.bookerbe.exception.BusinessRuleValidationException;
import sk.isdd.workshop.bookerbe.exception.CodeListNotFoundException;
import sk.isdd.workshop.bookerbe.exception.CryptographicException;
import sk.isdd.workshop.bookerbe.exception.DataValidationException;
import sk.isdd.workshop.bookerbe.exception.FilterValidationException;
import sk.isdd.workshop.bookerbe.exception.ForbiddenException;
import sk.isdd.workshop.bookerbe.exception.InvalidPagingException;
import sk.isdd.workshop.bookerbe.exception.InvalidSortingException;
import sk.isdd.workshop.bookerbe.exception.LargeAccountException;
import sk.isdd.workshop.bookerbe.exception.MessageContextValidationException;
import sk.isdd.workshop.bookerbe.exception.MsisdnIsOrangeCustomerException;
import sk.isdd.workshop.bookerbe.exception.ObsoleteResourceException;
import sk.isdd.workshop.bookerbe.exception.RecordBlacklistedException;
import sk.isdd.workshop.bookerbe.exception.RecordNotFoundException;
import sk.isdd.workshop.bookerbe.exception.SystemException;
import sk.isdd.workshop.bookerbe.exception.basic.ApiException;
import sk.isdd.workshop.bookerbe.exception.basic.ErrorCode;
import sun.security.validator.ValidatorException;


/**
 * @Author Filip Stiglic
 */


@ControllerAdvice
@Order(10)
@Profile({"default","local","dev","testnb", "testeuro"})
public class RestExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler  {

	@Autowired
	protected ObjectMapper objectMapper;



	@Value("${logging.lms.active:false}")
	protected boolean lmsLoggingActive;

	@ExceptionHandler({RecordNotFoundException.class})
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {

		ApiException apiError =	new ApiException(HttpStatus.NOT_FOUND, ErrorCode.RECORD_NOT_FOUND, ex.getMessage(), "Record not found");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({RecordBlacklistedException.class})
	public ResponseEntity<Object> handleRecordBlacklistException(RecordBlacklistedException ex, WebRequest request) {

		//We dont want to log this error :)
		//logError(messageContext, ex);

		ApiException apiError =	new ApiException(HttpStatus.FORBIDDEN, ErrorCode.RECORD_BLACKLISTED, ex.getMessage(), "Record not found");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({CodeListNotFoundException.class})
	public ResponseEntity<Object> handleCodeListNotFoundException(CodeListNotFoundException ex, WebRequest request) {


		ApiException apiError =	new ApiException(getHttpMethod(request).equals(HttpMethod.GET)? HttpStatus.NOT_FOUND: HttpStatus.BAD_REQUEST, ErrorCode.CODELIST_NOT_FOUND, ex.getMessage(), "CodeList not found");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({AgreementSpecificationNotFoundException.class})
	public ResponseEntity<Object> handleAgreementSpecificationNotFoundException(AgreementSpecificationNotFoundException ex, WebRequest request) {



		ApiException apiError =	new ApiException(getHttpMethod(request).equals(HttpMethod.GET)? HttpStatus.NOT_FOUND: HttpStatus.BAD_REQUEST,ErrorCode.AGREEMENT_SPECIFICATION_NOT_FOUND, ex.getMessage(), "Agreement specification not found");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {



		if(isGetByIdRequest(ex)){
			ApiException apiError =	new ApiException(HttpStatus.NOT_FOUND,ErrorCode.RECORD_NOT_FOUND, "AgreementSpecification with id "+ex.getValue()+" was not found", "Record not found");
			return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
		}else{
			ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH, ex.getMessage(), ex.getName() + " should be of type " + ex.getRequiredType().getName());
			return new ResponseEntity<>(apiError, apiError.getStatus());
		}
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.MISSING_REQUEST_PARAMETERS, ex.getMessage(), "Missing request parameter");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.REQUEST_NOT_READABLE, ex.getMessage(), "Request not readable!");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {



		List<String> errors = Stream.concat(
									ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage()),
									ex.getBindingResult().getGlobalErrors().stream().map(globalError -> globalError.getObjectName() + ": " + globalError.getDefaultMessage())
		                      ).collect(Collectors.toList());

		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.REQUEST_NOT_VALID, "Request validation errror occured!", errors);
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({SystemException.class})
	public ResponseEntity<Object> handleSystemException(SystemException ex, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SYSTEM_ERROR, ex.getMessage(), "System error occured");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ValidatorException.class})
	public ResponseEntity<Object> handleValidatorException(ValidatorException ex, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.REQUEST_NOT_VALID, ex.getMessage(), "Validator error occured");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({RestClientException.class})
	public ResponseEntity<Object> handleRestClientException(RestClientException ex, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.REST_CLIENT_ERROR, ex.getMessage(), "Rest client exception");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({HttpClientErrorException.class})
	public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex, WebRequest request) {

		ApiException apiError = getInnerRestApiClientException(ex);
		if(apiError==null){
			apiError = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.REST_CLIENT_ERROR, ex.getMessage(), "Rest client exception");
		}
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({HttpServerErrorException.class})
	public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex, WebRequest request) {

		ApiException apiError = getInnerRestApiClientException(ex);
		if(apiError==null){
			apiError = new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.REST_CLIENT_ERROR, ex.getMessage(), "Rest client exception");
		}
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({CryptographicException.class})
	public ResponseEntity<Object> handleCryptographicException(CryptographicException ex, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SYSTEM_ERROR, ex.getMessage(), "Crypto exception");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({DataValidationException.class})
	public ResponseEntity<Object> handleDataValidationException(DataValidationException ex, WebRequest request) {



		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST,ErrorCode.DATA_NOT_VALID, ex.getMessage(), "Message context validation occured");

		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({BusinessRuleValidationException.class})
	public ResponseEntity<Object> handleBusinessRuleValidationException(BusinessRuleValidationException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST,ErrorCode.BUSINESS_VALIDATION_FAILED, ex.getMessage(), ex.getMessage());

		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({MsisdnIsOrangeCustomerException.class})
	public ResponseEntity<Object> handleMsisdnIsOrangeCustomerException(MsisdnIsOrangeCustomerException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST,ErrorCode.MSISDN_IS_ORANGE_CUSTOMER, ex.getMessage(), ex.getMessage());

		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ObsoleteResourceException.class})
	public ResponseEntity<Object> handleObsoleteResourceException(ObsoleteResourceException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.PRECONDITION_FAILED,ErrorCode.NEWER_RESOURCE_EXISTS, ex.getMessage(), ex.getMessage());

		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({MessageContextValidationException.class})
	public ResponseEntity<Object> handleMessageContextValidationException(MessageContextValidationException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.MESSAGE_CONTEXT_NOT_VALID, ex.getMessage(), ex.getValidationErrors());

		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({FilterValidationException.class})
	public ResponseEntity<Object> handleFilterValidationException(FilterValidationException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.FILTER_NOT_VALID, ex.getMessage(), ex.getValidationErrors());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ForbiddenException.class})
	public ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.FORBIDDEN, ErrorCode.USER_NOT_AUTHORIZED, ex.getMessage(), "User is not authorized");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ActionForbiddenException.class})
	public ResponseEntity<Object> handleActionForbiddenException(ActionForbiddenException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.FORBIDDEN, ErrorCode.USER_NOT_AUTHORIZED, ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({LargeAccountException.class})
	public ResponseEntity<Object> handleLargeAccountException(LargeAccountException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.FORBIDDEN, ErrorCode.ACCOUNT_TOO_LARGE, ex.getMessage(), ex.getMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({AuthorizationException.class})
	public ResponseEntity<Object> handleAuthorizationServiceException(AuthorizationException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_NOT_AUTHENTICATED, ex.getMessage(), "User was not authenticated");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({SecurityException.class})
	public ResponseEntity<Object> handleSecurityException(SecurityException ex, WebRequest request) {

		ApiException apiError =	new ApiException(HttpStatus.UNAUTHORIZED, ErrorCode.USER_NOT_AUTHENTICATED, ex.getMessage(), "User was not authenticated");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({InvalidSortingException.class})
	public ResponseEntity<Object> handleInvalidSortingException(InvalidSortingException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.SORTING_NOT_VALID, ex.getMessage(), "Sorting parameter processing exception occured");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({InvalidPagingException.class})
	public ResponseEntity<Object> handleInvalidPagingException(InvalidPagingException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.PAGING_NOT_VALID, ex.getMessage(), "Paging parameter processing exception occured");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.NOT_FOUND,ErrorCode.HANDLER_NOT_FOUND, ex.getMessage(), "Handler not found");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.PATH_VARIABLE_NOT_FOUND , ex.getMessage(), "Misssing path variable");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.HTTP_METHOD_NOT_SUPPORTED, ex.getMessage(), String.format("Http method %s not supported",ex.getMethod()));
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler({AgreementExistsException.class})
	public ResponseEntity<Object> handleAgreementSpecificationNotFoundException(AgreementExistsException ex, WebRequest request) {


		ApiException apiError =	new ApiException(HttpStatus.CONFLICT,ErrorCode.AGREEMENT_ALREADY_EXISTS, ex.getMessage(), "Agreement with this type and party role already exists");
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ApiException> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {


		ApiException apiError =	new ApiException(
			HttpStatus.BAD_REQUEST, ErrorCode.CONSTRAINT_VIOLATION, ex.getMessage(), ex.getConstraintViolations().stream().map(constraintViolation -> String.format("%s : %s", constraintViolation.getPropertyPath(),constraintViolation.getMessage())).collect(Collectors.toList()));
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	protected boolean isGetByIdRequest(MethodArgumentTypeMismatchException ex){

		if(ex.getName().toLowerCase().equals("id") && ex.getParameter().getParameterType().equals(Long.class)){
			return true;
		}
		return false;
	}

	protected ApiException getInnerRestApiClientException(RestClientResponseException ex){
		//Need to check whether the HttpClientErrorException body is JSON serializable into ApiException
		//That means that the exception comes from our api
		try{
			return objectMapper.readValue(ex.getResponseBodyAsByteArray(), ApiException.class);
		}catch (IOException e){
			return null;
		}
	}

	protected HttpMethod getHttpMethod(WebRequest request){
		return ((ServletWebRequest) request).getHttpMethod();
	}



}

