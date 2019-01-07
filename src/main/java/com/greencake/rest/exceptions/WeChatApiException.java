package com.greencake.rest.exceptions;

public class WeChatApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMessage;

	public WeChatApiException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorCode: " + errorCode + ", ErrorMsg: " + errorMessage;
	}

}
