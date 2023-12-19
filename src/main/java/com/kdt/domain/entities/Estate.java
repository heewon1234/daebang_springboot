package com.kdt.domain.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Real_Estate")
public class Estate {

	@Id
	@Column(name = "estate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long estateId;

//	@Column(name = "writer")
//	private String writer;

	@Column(name = "deposit")
	private Long deposit;

	@Column(name = "price")
	private Long price;

	@Column(name = "area")
	private float area;

	@Column(name = "zipcode")
	private Long zipcode;

	@Column(name = "address1")
	private String address1;
	
	@Column(name = "address2")
	private String address2;

	@Column(name = "latitude")
	private float latitude;

	@Column(name = "longitude")
	private float longitude;

	@Column(name = "room_floors")
	private Long roomFloors;

	@Column(name = "building_floors")
	private Long buildingFloors;
	
	@Column(name = "maintenance_cost")
	private Long maintenanceCost;

	@Column(name = "title")
	private String title;

	@Column(name = "contents")
	private String contents;

	@Column(name = "memo")
	private String memo;

	@Column(name = "write_date")
	private Timestamp writeDate;
	
	@OneToOne
	@JoinColumn(name = "room_code", referencedColumnName = "room_id")
	private Room room;

	@OneToOne
	@JoinColumn(name = "structure_code", referencedColumnName = "structure_id")
	private Structure structure;

	@OneToOne
	@JoinColumn(name = "building_code", referencedColumnName = "building_id")
	private Building building;

	@OneToOne
	@JoinColumn(name = "transaction_code", referencedColumnName = "transaction_id")
	private Transaction transaction;

	@OneToOne
	@JoinColumn(name = "heating_code", referencedColumnName = "heating_id")
	private HeatingSystem heatingSystem;

	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "estate_code")
	private Set<EstateOption> optionList;

	@OneToMany(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "parent_id")
	private Set<EstateImage> images;
	
	@OneToOne
    @JoinColumn(name = "writer", referencedColumnName = "email")
    private Real_Estate_Agent realEstateAgent;

	public Long getEstateId() {
		return estateId;
	}

	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}

//	public String getWriter() {
//		return writer;
//	}
//
//	public void setWriter(String writer) {
//		this.writer = writer;
//	}

	public Real_Estate_Agent getRealEstateAgent() {
		return realEstateAgent;
	}

	public void setRealEstateAgent(Real_Estate_Agent realEstateAgent) {
		this.realEstateAgent = realEstateAgent;
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public HeatingSystem getHeatingSystem() {
		return heatingSystem;
	}

	public void setHeatingSystem(HeatingSystem heatingSystem) {
		this.heatingSystem = heatingSystem;
	}

	public Set<EstateOption> getOptionList() {
		return optionList;
	}

	public void setOptionList(Set<EstateOption> optionList) {
		this.optionList = optionList;
	}

	public Set<EstateImage> getImages() {
		return images;
	}

	public void setImages(Set<EstateImage> images) {
		this.images = images;
	}

//	public Estate(Long estateId, String writer, Long deposit, Long price, float area, Long zipcode, String address1,
//			String address2, float latitude, float longitude, Long roomFloors, Long buildingFloors,
//			Long maintenanceCost, String title, String contents, String memo, Timestamp writeDate, Room room,
//			Structure structure, Building building, Transaction transaction, HeatingSystem heatingSystem,
//			Set<EstateOption> optionList, Set<EstateImage> images) {
//		super();
//		this.estateId = estateId;
//		this.writer = writer;
//		this.deposit = deposit;
//		this.price = price;
//		this.area = area;
//		this.zipcode = zipcode;
//		this.address1 = address1;
//		this.address2 = address2;
//		this.latitude = latitude;
//		this.longitude = longitude;
//		this.roomFloors = roomFloors;
//		this.buildingFloors = buildingFloors;
//		this.maintenanceCost = maintenanceCost;
//		this.title = title;
//		this.contents = contents;
//		this.memo = memo;
//		this.writeDate = writeDate;
//		this.room = room;
//		this.structure = structure;
//		this.building = building;
//		this.transaction = transaction;
//		this.heatingSystem = heatingSystem;
//		this.optionList = optionList;
//		this.images = images;
//	}

	public Estate() {
		super();
	}

public Estate(Long estateId, Long deposit, Long price, float area, Long zipcode, String address1, String address2,
		float latitude, float longitude, Long roomFloors, Long buildingFloors, Long maintenanceCost, String title,
		String contents, String memo, Timestamp writeDate, Room room, Structure structure, Building building,
		Transaction transaction, HeatingSystem heatingSystem, Set<EstateOption> optionList, Set<EstateImage> images,
		Real_Estate_Agent realEstateAgent) {
	super();
	this.estateId = estateId;
	this.deposit = deposit;
	this.price = price;
	this.area = area;
	this.zipcode = zipcode;
	this.address1 = address1;
	this.address2 = address2;
	this.latitude = latitude;
	this.longitude = longitude;
	this.roomFloors = roomFloors;
	this.buildingFloors = buildingFloors;
	this.maintenanceCost = maintenanceCost;
	this.title = title;
	this.contents = contents;
	this.memo = memo;
	this.writeDate = writeDate;
	this.room = room;
	this.structure = structure;
	this.building = building;
	this.transaction = transaction;
	this.heatingSystem = heatingSystem;
	this.optionList = optionList;
	this.images = images;
	this.realEstateAgent = realEstateAgent;
}

}