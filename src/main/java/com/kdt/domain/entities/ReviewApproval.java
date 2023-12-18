package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Review_Approval")
public class ReviewApproval {

	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="user_id")
	private String userId;
	
	@Column(name="estate_code")
	private Long estateCode;
	
	@Column(name="approval_code")
	private String approvalCode;
	
	@Column(name="write_date")
	private Timestamp writeDate;

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

	public Long getEstateCode() {
		return estateCode;
	}

	public void setEstateCode(Long estateCode) {
		this.estateCode = estateCode;
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

	public ReviewApproval(Long seq, String userId, Long estateCode, String approvalCode, Timestamp writeDate) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.estateCode = estateCode;
		this.approvalCode = approvalCode;
		this.writeDate = writeDate;
	}

	public ReviewApproval() {
		super();
	}

}
