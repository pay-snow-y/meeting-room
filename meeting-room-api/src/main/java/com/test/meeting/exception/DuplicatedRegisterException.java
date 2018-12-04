package com.test.meeting.exception;


public class DuplicatedRegisterException extends BaseException {
	public DuplicatedRegisterException() {
		super(ExceptionCodes.EC001_CALENDAR_REGISTER_DUPLICATE);
	}
}
