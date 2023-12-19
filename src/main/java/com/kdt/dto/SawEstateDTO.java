package com.kdt.dto;

public class SawEstateDTO {
	private String address;
	private String title;
	private String approvalCode;
	private String img;
	private Long estateId;
	public SawEstateDTO() {
		super();
	}
	public SawEstateDTO(String address, String title, String approvalCode, String img) {
		super();
		this.address = address;
		this.title = title;
		this.approvalCode = approvalCode;
		this.img = img;
	}
	public SawEstateDTO(String address, String title, String approvalCode, String img,Long estateId) {
		super();
		this.address = address;
		this.title = title;
		this.approvalCode = approvalCode;
		this.img = img;
		this.estateId = estateId;
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
