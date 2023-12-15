package com.kdt.dto;

import java.sql.Timestamp;

public class ReviewApprovalDTO {

	private Long seq; 
	private String userId;
	private Long estateCode;
	private String approvalCode;
	private Timestamp write_date;
	public ReviewApprovalDTO() {
		super();
	}
	public ReviewApprovalDTO(Long seq, String userId, Long estateCode, String approvalCode, Timestamp write_date) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.estateCode = estateCode;
		this.approvalCode = approvalCode;
		this.write_date = write_date;
	}
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
	public Timestamp getWrite_date() {
		return write_date;
	}
	public void setWrite_date(Timestamp write_date) {
		this.write_date = write_date;
	}
	
	
}
