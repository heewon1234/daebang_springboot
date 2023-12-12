package com.kdt.dto;

import java.sql.Timestamp;

public class EstateDTO {
	private Long estateId;
	private String writer;
	private String roomType;
	private String structureType;
	private String buildingType;
	private String transactionType;
	private String heatingType;
	private Long deposit;
	private Long price;
	private float area;
	private Long zipcode;
	private String address;
	private float latitude;
	private float longitude;
	private Long roomFloors;
	private Long buildingFloors;
	private Long maintenanceCost;
	private String title;
	private String contents;
	private String memo;
	private Timestamp writeDate;
	public Long getEstateId() {
		return estateId;
	}
	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getStructureType() {
		return structureType;
	}
	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}
	public String getBuildingType() {
		return buildingType;
	}
	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getHeatingType() {
		return heatingType;
	}
	public void setHeatingType(String heatingType) {
		this.heatingType = heatingType;
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
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
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
	public EstateDTO(Long estateId, String writer, String roomType, String structureType, String buildingType,
			String transactionType, String heatingType, Long deposit, Long price, float area, Long zipcode,
			String address, float latitude, float longitude, Long roomFloors, Long buildingFloors, Long maintenanceCost,
			String title, String contents, String memo, Timestamp writeDate) {
		super();
		this.estateId = estateId;
		this.writer = writer;
		this.roomType = roomType;
		this.structureType = structureType;
		this.buildingType = buildingType;
		this.transactionType = transactionType;
		this.heatingType = heatingType;
		this.deposit = deposit;
		this.price = price;
		this.area = area;
		this.zipcode = zipcode;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
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
