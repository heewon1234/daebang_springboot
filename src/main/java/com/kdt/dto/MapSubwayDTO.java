package com.kdt.dto;

public class MapSubwayDTO {
	private int seq;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    
	public MapSubwayDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapSubwayDTO(int seq, String name, String address, String latitude, String longitude) {
		super();
		this.seq = seq;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	
    
}
