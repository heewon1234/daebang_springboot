package com.kdt.dto;

import java.time.Instant;
import java.util.Set;

public class ReviewDTO {
	
	private Long seq;
	private String id;
	private String realEstateNumber;
	private Long estateId;
	private String approvalCode;
	private String traffic;
	private String surroundings;
	private String facility;
	private Long score;
	private Instant writeDate;
	private Set<ReviewFilesDTO> files;
	
	public ReviewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewDTO(Long seq, String id, String realEstateNumber, Long estateId, String approvalCode, String traffic,
			String surroundings, String facility, Long score, Instant writeDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.realEstateNumber = realEstateNumber;
		this.estateId = estateId;
		this.approvalCode = approvalCode;
		this.traffic = traffic;
		this.surroundings = surroundings;
		this.facility = facility;
		this.score = score;
		this.writeDate = writeDate;
	}
	
	
	public Set<ReviewFilesDTO> getFiles() {
		return files;
	}
	public void setFiles(Set<ReviewFilesDTO> files) {
		this.files = files;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealEstateNumber() {
		return realEstateNumber;
	}
	public void setRealEstateNumber(String realEstateNumber) {
		this.realEstateNumber = realEstateNumber;
	}
	public Long getEstateId() {
		return estateId;
	}
	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}
	public String getApprovalCode() {
		return approvalCode;
	}
	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getSurroundings() {
		return surroundings;
	}
	public void setSurroundings(String surroundings) {
		this.surroundings = surroundings;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Instant getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Instant writeDate) {
		this.writeDate = writeDate;
	}

}
