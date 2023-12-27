package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Map_search_school")
public class MapSchool {

    @Id // 기본 키 필드가 있다고 가정합니다.
    @Column(name="seq")
    private int seq;
    
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

	public MapSchool() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapSchool(int seq, String name, String address, String latitude, String longitude) {
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
