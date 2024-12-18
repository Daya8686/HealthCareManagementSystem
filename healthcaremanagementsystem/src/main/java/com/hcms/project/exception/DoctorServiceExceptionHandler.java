package com.hcms.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DoctorServiceExceptionHandler extends RuntimeException {
	
	private HttpStatus httpStatus;
	
	public DoctorServiceExceptionHandler(String message, HttpStatus httpStatus) {
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
