package com.kdt.dto;

public class RealEstateAgentDTO {
private String email;
	private String pw;
	private String estateName;
	private String estateNumber;
	private String name;
	private String address;
	private String phone;
	private double manners_temperature;
	private String role;
	private boolean enabled;
	
	public RealEstateAgentDTO(String email, String pw, String estateName, String estateNumber, String name,
			String address, String phone, double manners_temperature, String role, boolean enabled) {
		super();
		this.email = email;
		this.pw = pw;
		this.estateName = estateName;
		this.estateNumber = estateNumber;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.manners_temperature = manners_temperature;
		this.role = role;
		this.enabled = enabled;
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




	public RealEstateAgentDTO() {
		super();
	}
	
}
