package com.test.meeting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.test.meeting.exception.BaseException;
import com.test.meeting.exception.CalendarNotFoundException;
import com.test.meeting.exception.DuplicatedRegisterException;
import com.test.meeting.exception.MeetingRoomNotFoundException;

@ControllerAdvice
public class CalendarApiExceptionHandler {
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(value = DuplicatedRegisterException.class)
	public void handleDuplicatedRegisterException(DuplicatedRegisterException e) {
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = CalendarNotFoundException.class)
	public void handleCalendarNotFoundException(CalendarNotFoundException e) {
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = MeetingRoomNotFoundException.class)
	public void handleMeetingRoomNotFoundException(MeetingRoomNotFoundException e) {
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
