package com.kdt.dto;

import java.sql.Timestamp;

import com.kdt.domain.entities.Estate;

public class ReviewApprovalDTO {

	private Long seq; 
	private String userId;
//	private Long estateCode;
	
	private String approvalCode;
	private Timestamp write_date;
	private Estate estate;
	private Long estateCode;
	private String estateName;
	
	
	public ReviewApprovalDTO(Long seq, String userId, String approvalCode, Timestamp write_date, String estateName,Long estateCode) {
		super();
		this.seq = seq;
		this.userId = userId;
		this.approvalCode = approvalCode;
		this.write_date = write_date;
		this.write_date = write_date;
		if (estate != null) {
		    this.estateCode = estate.getEstateId();
		} else {
		    this.estateCode = null;
		}

	    if (estate != null && estate.getRealEstateAgent() != null) {
	        this.estateName = estate.getRealEstateAgent().getEstateName();
	    } else {
	        this.estateName = null;
	    }
	}
	
	public Long getEstateCode() {
        if (estate != null) {
            return estate.getEstateId();
        } else {
            return null;
        }
    }

	public void setEstateCode(Long estateCode) {
		this.estateCode = estateCode;
	}

	public String getEstateName() {
		 if (estate != null && estate.getRealEstateAgent() != null) {
	            return estate.getRealEstateAgent().getEstateName();
	        } else {
	            return null; // 혹은 다른 기본값 또는 예외 처리 방법을 선택할 수 있습니다.
	        }
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public Estate getEstate() {
		return estate;
	}
	public void setEstate(Estate estate) {
		this.estate = estate;
	}
	public ReviewApprovalDTO() {
		super();
	}
//	public ReviewApprovalDTO(Long seq, String userId, Long estateCode, String approvalCode, Timestamp write_date) {
//		super();
//		this.seq = seq;
//		this.userId = userId;
//		this.estateCode = estateCode;
//		this.approvalCode = approvalCode;
//		this.write_date = write_date;
//	}
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
//	public Long getEstateCode() {
//		return estateCode;
//	}
//	public void setEstateCode(Long estateCode) {
//		this.estateCode = estateCode;
//	}
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
