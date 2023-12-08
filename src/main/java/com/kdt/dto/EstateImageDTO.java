package com.kdt.dto;

public class EstateImageDTO {
	private Long seq;
	private String oriName;
	private String sysName;
	private Long parentSeq;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public Long getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}

	public EstateImageDTO(Long seq, String oriName, String sysName, Long parentSeq) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentSeq = parentSeq;
	}

	public EstateImageDTO() {
		super();
	}
	
	
}
