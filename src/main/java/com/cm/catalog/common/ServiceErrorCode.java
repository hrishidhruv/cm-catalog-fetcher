package com.cm.catalog.common;

/**
 * Enum of Service Exceptions
 * @author rdhruv
 *
 */
public enum ServiceErrorCode implements ErrorCode {
	UNEXPECTED_EXCEPTION(100),
	UNKNOWN_HOST(101),
	RESOURCE_NOT_FOUND(102), 
	WEB_API_BASE_URL_NOT_SET(103),
	CATEGORY_NOT_FOUND(104);
	
	
	private final int errorCode;
	
	private ServiceErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
	
}
