package com.test.meeting.domain;

public class Calendar {
	private int calId;
	private int roomId;
	private String regUser;
	private String regYmd;
	private String startTime;
	private String endTime;
	private String repeatYn;
	private int repeatCount;

	public Calendar(int roomId, String regUser, String regYmd, String startTime, String endTime, String repeatYn, int repeatCount) {
		this.roomId = roomId;
		this.regUser = regUser;
		this.regYmd = regYmd;
		this.startTime = startTime;
		this.endTime = endTime;
		this.repeatYn = repeatYn;
		this.repeatCount = repeatCount;
	}

	public Calendar() {
	}
	
	public String getRepeatYn() {
		return repeatYn;
	}

	public void setRepeatYn(String repeatYn) {
		this.repeatYn = repeatYn;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public int getCalId() {
		return calId;
	}

	public void setCalId(int calId) {
		this.calId = calId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
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
		return "<h4>" + regUser + "</h4>"
				+ startTime + "<br/>" + endTime;
	}
}
