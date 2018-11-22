package com.test.meeting.domain;

public class CalendarDto {
	private int calId;
	private int meetingRoomId;
	private String regUser;
	private String regYmd;
	private String startTime;
	private String endTime;

	CalendarDto() {
	}

	public int getCalId() {
		return calId;
	}

	public void setCalId(int calId) {
		this.calId = calId;
	}

	public int getMeetingRoomId() {
		return meetingRoomId;
	}

	public void setMeetingRoomId(int meetingRoomId) {
		this.meetingRoomId = meetingRoomId;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getRegYmd() {
		return regYmd;
	}

	public void setRegYmd(String regYmd) {
		this.regYmd = regYmd;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "calendarDTO [calId=" + calId + ", meetingRoomId=" + meetingRoomId + ", regUser=" + regUser + ", regYmd="
				+ regYmd + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
