package com.kdt.dto;

import java.time.Instant;

public class RealEstateAgentDTO {
private String email;
	private String pw;
	private String estateName;
	private String estateNumber;
	private String name;
	private String address;
	private String phone;
	private double manners_temperature;
	private double latitude;
	private double longitude;
	private String role;
	private boolean enabled;
	private String content;
	private Long report_Count;
	private Instant signupDate;
	
	public RealEstateAgentDTO(String email, String pw, String estateName, String estateNumber, String name,
			String address, String phone, double manners_temperature, double latitude, double longitude, String role,
			boolean enabled, String content) {
		super();
		this.email = email;
		this.pw = pw;
		this.estateName = estateName;
		this.estateNumber = estateNumber;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.manners_temperature = manners_temperature;
		this.latitude = latitude;
		this.longitude = longitude;
		this.role = role;
		this.enabled = enabled;
		this.content = content;
	}
	public RealEstateAgentDTO(String email, String pw, String estateName, String estateNumber, String name,
			String address, String phone, double manners_temperature, double latitude, double longitude, String role,
			boolean enabled, String content, Long report_Count, Instant signupDate) {
		super();
		this.email = email;
		this.pw = pw;
		this.estateName = estateName;
		this.estateNumber = estateNumber;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.manners_temperature = manners_temperature;
		this.latitude = latitude;
		this.longitude = longitude;
		this.role = role;
		this.enabled = enabled;
		this.content = content;
		this.report_Count = report_Count;
		this.signupDate = signupDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getEstateName() {
		return estateName;
	}


	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}


	public String getEstateNumber() {
		return estateNumber;
	}


	public void setEstateNumber(String estateNumber) {
		this.estateNumber = estateNumber;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public double getManners_temperature() {
		return manners_temperature;
	}


	public void setManners_temperature(double manners_temperature) {
		this.manners_temperature = manners_temperature;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Long getReport_Count() {
		return report_Count;
	}


	public void setReport_Count(Long report_Count) {
		this.report_Count = report_Count;
	}


	public Instant getSignupDate() {
		return signupDate;
	}


	public void setSignupDate(Instant signupDate) {
		this.signupDate = signupDate;
	}


	public RealEstateAgentDTO() {
		super();
	}
	
}