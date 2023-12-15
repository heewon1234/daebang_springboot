package com.kdt.dto;

public class MapSchoolDTO {
	private String seq;
    private String name;
    private String latitude;
    private String longitude;
    
	public MapSchoolDTO(String seq, String name, String latitude, String longitude) {
		super();
		this.seq = seq;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public MapSchoolDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
