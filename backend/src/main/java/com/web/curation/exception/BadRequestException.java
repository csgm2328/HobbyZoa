package com.web.curation.exception;

import org.hibernate.validator.internal.engine.validationcontext.ValidationContext;

public class BadRequestException extends RuntimeException{
	private String errorCode;
	/**
     * default 400 Error
     */
//    public BadRequestException() {
//        this.errorCode = EnvironmentCode.INVALID_REQUEST_DATA;
//    }

    /**
     * Error Code 지정
     * @param code
     */
    public BadRequestException(String code) {
        this.errorCode = code;
    }
//    public BadRequestException(ValidationContext<String> code) {
//    	this.errorCode = code;
//    }
    	

    public String getErrorCode() {
        return errorCode;
    }
}
