package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Real_Estate_Agent {
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="pw")
	private String pw;
	
	@Column(name="name")
	private String name;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="manners_temperature")
	private double manners_temperature;
	
	@Column(name="role")
	private String role;
	
	@Column(name="enabled")
	private boolean enabled;


	public Real_Estate_Agent(String email, String pw, String name, String phone, double manners_temperature,
			String role, boolean enabled) {
		super();
		this.email = email;
		this.pw = pw;
		this.name = name;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
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


	public Real_Estate_Agent() {
		super();
	}
	
	
}
