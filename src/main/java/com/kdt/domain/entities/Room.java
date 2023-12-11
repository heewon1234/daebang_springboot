package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Room")
public class Room {
	
	@Id
	@Column(name="room_id")
	private String roomId;
	
	@Column(name="room_type")
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

	public Room(String roomId, String roomType) {
		super();
		this.roomId = roomId;
		this.roomType = roomType;
	}

	public Room() {
		super();
	}
	
}
