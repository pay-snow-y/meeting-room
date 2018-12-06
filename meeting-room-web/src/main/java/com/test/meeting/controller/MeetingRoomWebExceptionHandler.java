package com.test.meeting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.test.meeting.exception.BaseException;
import com.test.meeting.exception.CalendarNotFoundException;
import com.test.meeting.exception.DuplicatedRegisterException;
import com.test.meeting.exception.MeetingRoomNotFoundException;

@ControllerAdvice
public class MeetingRoomWebExceptionHandler {

	@ExceptionHandler(value = BaseException.class)
	public ModelAndView handleBaseException(BaseException e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/error_common");
		modelAndView.addObject("exceptionMessage", e.getMessage());

		return modelAndView;
	}

	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/error_common");
		modelAndView.addObject("exceptionMessage", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

		return modelAndView;
	}
}
