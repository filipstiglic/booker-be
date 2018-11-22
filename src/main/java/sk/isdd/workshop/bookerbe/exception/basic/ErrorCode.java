package sk.isdd.workshop.bookerbe.exception.basic;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @Author Filip Stiglic
 */
public enum ErrorCode {

	RECORD_NOT_FOUND(Category.BUSINESS, "001"),
	CODELIST_NOT_FOUND(Category.BUSINESS, "002"),
	METHOD_ARGUMENT_TYPE_MISMATCH(Category.BUSINESS, "003"),
	MISSING_REQUEST_PARAMETERS(Category.BUSINESS, "004"),
	REQUEST_NOT_READABLE(Category.BUSINESS, "005"),
	REQUEST_NOT_VALID(Category.BUSINESS, "006"),
	USER_NOT_AUTHENTICATED(Category.BUSINESS, "007"),
	USER_NOT_AUTHORIZED(Category.BUSINESS, "008"),
	DATA_NOT_VALID(Category.BUSINESS, "009"),
	MESSAGE_CONTEXT_NOT_VALID(Category.BUSINESS, "010"),
	FILTER_NOT_VALID(Category.BUSINESS, "011"),
	SORTING_NOT_VALID(Category.BUSINESS, "012"),
	PAGING_NOT_VALID(Category.BUSINESS, "013"),
	HANDLER_NOT_FOUND(Category.BUSINESS, "014"),
	PATH_VARIABLE_NOT_FOUND(Category.BUSINESS, "015"),
	HTTP_METHOD_NOT_SUPPORTED(Category.BUSINESS, "016"),
	CONSTRAINT_VIOLATION(Category.BUSINESS, "017"),

	SYSTEM_ERROR(Category.SYSTEM, "018"),
	REST_CLIENT_ERROR(Category.SYSTEM, "019"),

	AGREEMENT_SPECIFICATION_NOT_FOUND(Category.BUSINESS, "020"),
	AGREEMENT_ALREADY_EXISTS(Category.BUSINESS, "021"),
	BUSINESS_VALIDATION_FAILED(Category.BUSINESS, "022"),
	NEWER_RESOURCE_EXISTS(Category.BUSINESS, "023"),

	ACCOUNT_TOO_LARGE(Category.BUSINESS, "024"),
	RECORD_BLACKLISTED(Category.BUSINESS, "025"),

	MSISDN_IS_ORANGE_CUSTOMER(Category.BUSINESS, "026");

	String code;
	Category category;

	ErrorCode(Category category, String code){
		this.category = category;
		this.code = code;
	}

	@Override
	@JsonValue
	public String toString() {
		return category+"-"+code;
	}

	public enum Category{
		BUSINESS, SYSTEM
	}
}
