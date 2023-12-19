package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SawEstate {

	@Id
	@Column(name="address1")
	private String address;
	
	@Column(name="title")
	private String title;
	
	@Column(name="approval_code")
	private String approvalCode;
	
	@Column(name="img")
	private String img;

	@Column(name="estate_id")
	private Long estateId;
	
	public SawEstate() {
		super();
	}

	public SawEstate(String address, String title, String approvalCode, String img) {
		super();
		this.address = address;
		this.title = title;
		this.approvalCode = approvalCode;
		this.img = img;
	}

	public SawEstate(String address, String title, String approvalCode, String img, Long estateId) {
		super();
		this.address = address;
		this.title = title;
		this.approvalCode = approvalCode;
		this.img = img;
		this.estateId=estateId;
	}
	
	public Long getestateId() {
		return estateId;
	}

	public void setestateId(Long estateId) {
		this.estateId = estateId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
