package com.kdt.dto;

public class RoomDTO {
	private String roomId;
	private String roomType;
	
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public RoomDTO(String roomId, String roomType) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
	}
	public RoomDTO() {
		super();
	}
}
