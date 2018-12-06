package com.test.meeting.exception;

public class MeetingRoomNotFoundException extends BaseException {
	public MeetingRoomNotFoundException() {
		super(ExceptionCodes.EC002_ROOM_ID_IS_NOT_EXIST);
	}
}
