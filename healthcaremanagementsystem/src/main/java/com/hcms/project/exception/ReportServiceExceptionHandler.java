package com.hcms.project.exception;

import org.springframework.http.HttpStatus;

public class ReportServiceExceptionHandler extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	public ReportServiceExceptionHandler(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus=httpStatus;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
