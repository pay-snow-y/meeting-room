package com.test.meeting.exception;

public class DuplicatedRegisterException extends BaseException {
	public DuplicatedRegisterException() {
		super(ExceptionCodes.CALENDAR_REGISTER_DUPLICATE);
	}
}
