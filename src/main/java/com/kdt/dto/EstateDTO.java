package com.kdt.dto;

public class EstateDTO {
	private String roomCode;
	private String buildingCode;
	private String heatingCode;
	private float area;
	private Long zipcode;
	private String address;
	
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	public String getHeatingCode() {
		return heatingCode;
	}
	public void setHeatingCode(String heatingCode) {
		this.heatingCode = heatingCode;
	}
	public float getArea() {
		return area;
	}
	public void setArea(float area) {
		this.area = area;
	}
	public Long getZipcode() {
		return zipcode;
	}
	public void setZipcode(Long zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public EstateDTO(String roomCode, String buildingCode, String heatingCode, float area, Long zipcode,
			String address) {
		super();
		this.roomCode = roomCode;
		this.buildingCode = buildingCode;
		this.heatingCode = heatingCode;
		this.area = area;
		this.zipcode = zipcode;
		this.address = address;
	}
	public EstateDTO() {
		super();
	}
	

}
