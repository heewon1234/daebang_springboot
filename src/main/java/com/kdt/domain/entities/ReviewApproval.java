package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Review_Approval")
public class ReviewApproval {

	@Id
	@Column(name = "seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "approval_code")
	private String approvalCode;

	@Column(name = "write_date")
	private Timestamp writeDate;
	
	@Column(name = "phone")
	private String phone;

	@ManyToOne
	@JoinColumn(name = "estate_code", referencedColumnName = "estate_id")
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

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
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

	public ReviewApproval(Long seq, String userId, String approvalCode, Timestamp writeDate, String phone,
			Estate estate) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.approvalCode = approvalCode;
		this.writeDate = writeDate;
		this.phone = phone;
		this.estate = estate;
	}

	public ReviewApproval() {
		super();
	}

	

}
