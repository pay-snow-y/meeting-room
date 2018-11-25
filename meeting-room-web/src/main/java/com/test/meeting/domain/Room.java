package com.test.meeting.domain;

public class Room {
	private int roomId;
	private String roomName;

	public Room(String roomName) {
		this.roomName = roomName;
	}

	public Room() {
	}
	
	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomName=" + roomName + "]";
	}
}
