package com.test.meeting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.meeting.exception.BaseException;
import com.test.meeting.exception.DuplicatedRegisterException;

@ControllerAdvice
public class CalendarApiExceptionHandler {
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = DuplicatedRegisterException.class)
	public void handleDuplicatedRegisterException(DuplicatedRegisterException e) {
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = BaseException.class)
	public void handleBaseException(BaseException e) {
	}

	@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(value = Exception.class)
	public void handleException(Exception e) {
	}
}
