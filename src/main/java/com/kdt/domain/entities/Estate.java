package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Real_Estate")
public class Estate {
	
	@Id
	@Column(name="estate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long estateId;
	
	@Column(name="room_code")
	private String roomCode;
	
	@Column(name="structure_code")
	private String structureCode;
	
	@Column(name="building_code")
	private String buildingCode;
	
	@Column(name="transaction_code")
	private String transactionCode;
	
	@Column(name="heating_code")
	private String heatingCode;
	
	@Column(name="deposit")
	private Long deposit;
	
	@Column(name="price")
	private Long price;
	
	@Column(name="area")
	private float area;
	
	@Column(name="zipcode")
	private Long zipcode;
	
	@Column(name="address")
	private String address;
	
	@Column(name="room_floors")
	private Long roomFloors;
	
	@Column(name="building_floors")
	private Long buildingFloors
	;
	@Column(name="maintenance_cost")
	private Long maintenanceCost;
	
	@Column(name="title")
	private String title;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="memo")
	private String memo;
	
	@Column(name="write_date")
	private Timestamp writeDate;

	public Long getEstateId() {
		return estateId;
	}

	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}

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

	public Estate(Long estateId, String roomCode, String structureCode, String buildingCode, String transactionCode,
			String heatingCode, Long deposit, Long price, float area, Long zipcode, String address, Long roomFloors,
			Long buildingFloors, Long maintenanceCost, String title, String contents, String memo,
			Timestamp writeDate) {
		super();
		this.estateId = estateId;
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

	public Estate() {
		super();
	}
	
	
}
