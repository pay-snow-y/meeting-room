package com.test.meeting.exception;

public class CalendarNotFoundException extends BaseException {
	public CalendarNotFoundException() {
		super(ExceptionCodes.EC003_CALENDAR_IS_NOT_EXIST);
	}
}
