package com.test.meeting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.meeting.exception.BaseException;

@ControllerAdvice
@RestController
public class CalendarApiExceptionHandler {

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = BaseException.class)
	public String handleBaseException(BaseException e) {
		return e.getMessage();
	}

	@ExceptionHandler(value = Exception.class)
	public String handleException(Exception e) {
		return e.getMessage();
	}
}
