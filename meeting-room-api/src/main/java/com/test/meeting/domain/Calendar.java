package com.test.meeting.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Calendar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int calId;
	private int meetingRoomId;
	private String regUser;
	private String regYmd;
	private String startTime;
	private String endTime;

	public Calendar(int meetingRoomId, String regUser) {
		this.meetingRoomId = meetingRoomId;
		this.regUser = regUser;
	}

	public Calendar() {
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
		return "calendar [calId=" + calId + ", meetingRoomId=" + meetingRoomId + ", regUser=" + regUser + ", regYmd="
				+ regYmd + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}
