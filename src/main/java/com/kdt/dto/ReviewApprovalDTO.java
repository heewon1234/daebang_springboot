package com.kdt.dto;

import java.sql.Timestamp;

import com.kdt.domain.entities.Estate;

public class ReviewApprovalDTO {

	private Long seq; 
	private String userId;
	private String approvalCode;
	private Timestamp write_date;
	private String phone;
	private Estate estate;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Estate getEstate() {
		return estate;
	}
	public void setEstate(Estate estate) {
		this.estate = estate;
	}
	public ReviewApprovalDTO(Long seq, String userId, String approvalCode, Timestamp write_date, String phone,
			Estate estate) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.approvalCode = approvalCode;
		this.write_date = write_date;
		this.phone = phone;
		this.estate = estate;
	}
	public ReviewApprovalDTO() {
		super();
	}
}
