package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Review_Approval")
public class ReviewApproval {

	@Id
	@Column(name="seq")
	private Long seq;
	
	@Column(name="user_id")
	private String user_id;
	
	@Column(name="estate_code")
	private Long estate_code;
	
	@Column(name="approval_code")
	private String approval_code;
	
	@Column(name="write_date")
	private Timestamp write_date;

	public ReviewApproval() {
		super();
	}

	public ReviewApproval(Long seq, String user_id, Long estate_code, String approval_code, Timestamp write_date) {
		super();
		this.seq = seq;
		this.user_id = user_id;
		this.estate_code = estate_code;
		this.approval_code = approval_code;
		this.write_date = write_date;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getEstate_code() {
		return estate_code;
	}

	public void setEstate_code(Long estate_code) {
		this.estate_code = estate_code;
	}

	public String getApproval_code() {
		return approval_code;
	}

	public void setApproval_code(String approval_code) {
		this.approval_code = approval_code;
	}

	public Timestamp getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	
}
