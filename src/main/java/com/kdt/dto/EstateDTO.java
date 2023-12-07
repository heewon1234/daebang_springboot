package com.kdt.dto;

import java.sql.Timestamp;

public class EstateDTO {
	private String roomCode;
	private String structureCode;
	private String buildingCode;
	private String transactionCode;
	private String heatingCode;
	private Long deposit;
	private Long price;
	private float area;
	private Long zipcode;
	private String address;
	private Long roomFloors;
	private Long buildingFloors;
	private Long maintenanceCost;
	private String title;
	private String contents;
	private String memo;
	private Timestamp writeDate;
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getStructureCode() {
		return structureCode;
	}
	public void setStructureCode(String structureCode) {
		this.structureCode = structureCode;
	}
	public String getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getHeatingCode() {
		return heatingCode;
	}
	public void setHeatingCode(String heatingCode) {
		this.heatingCode = heatingCode;
	}
	public Long getDeposit() {
		return deposit;
	}
	public void setDeposit(Long deposit) {
		this.deposit = deposit;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
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
	public Long getRoomFloors() {
		return roomFloors;
	}
	public void setRoomFloors(Long roomFloors) {
		this.roomFloors = roomFloors;
	}
	public Long getBuildingFloors() {
		return buildingFloors;
	}
	public void setBuildingFloors(Long buildingFloors) {
		this.buildingFloors = buildingFloors;
	}
	public Long getMaintenanceCost() {
		return maintenanceCost;
	}
	public void setMaintenanceCost(Long maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	public EstateDTO(String roomCode, String structureCode, String buildingCode, String transactionCode,
			String heatingCode, Long deposit, Long price, float area, Long zipcode, String address, Long roomFloors,
			Long buildingFloors, Long maintenanceCost, String title, String contents, String memo,
			Timestamp writeDate) {
		super();
		this.roomCode = roomCode;
		this.structureCode = structureCode;
		this.buildingCode = buildingCode;
		this.transactionCode = transactionCode;
		this.heatingCode = heatingCode;
		this.deposit = deposit;
		this.price = price;
		this.area = area;
		this.zipcode = zipcode;
		this.address = address;
		this.roomFloors = roomFloors;
		this.buildingFloors = buildingFloors;
		this.maintenanceCost = maintenanceCost;
		this.title = title;
		this.contents = contents;
		this.memo = memo;
		this.writeDate = writeDate;
	}
	public EstateDTO() {
		super();
	}

}
