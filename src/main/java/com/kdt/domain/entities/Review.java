package com.kdt.domain.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Review")
public class Review {

	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long seq;
	
	@Column(name="id")
	private String id;
	
	@Column(name="real_estate_number")
	private String realEstateNumber;
	
	@Column(name="estate_id", insertable = false, updatable = false)
	private Long estateId;
	
	@Column(name="approval_code")
	private String approvalCode;
	
	@Column(name="traffic")
	private String traffic;
	
	@Column(name="surroundings")
	private String surroundings;
	
	@Column(name="facility")
	private String facility;
	
	@Column(name="score")
	private Long score;
	
	@Column(name="anonymous")
	private boolean anonymous;
	
	@Column(name="write_date")
	private Timestamp writeDate;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="parent_seq")
	private Set<ReviewFiles> files;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estate_id", referencedColumnName = "estate_id")
	private Estate estate;
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
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

	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}

	public Set<ReviewFiles> getFiles() {
		return files;
	}

	public void setFiles(Set<ReviewFiles> files) {
		this.files = files;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public Review(Long seq, String id, String realEstateNumber, Long estateId, String approvalCode, String traffic,
			String surroundings, String facility, Long score, boolean anonymous, Timestamp writeDate,
			Set<ReviewFiles> files, Estate estate) {
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
		this.anonymous = anonymous;
		this.writeDate = writeDate;
		this.files = files;
		this.estate = estate;
	}
	
	
}
