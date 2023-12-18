package com.kdt.dto;

public class ReviewFilesDTO {

	private Long seq;
	private String sysName;
	private String oriName;
	private Long parentSeq;
	public ReviewFilesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewFilesDTO(Long seq, String sysName, String oriName, Long parentSeq) {
		super();
		this.seq = seq;
		this.sysName = sysName;
		this.oriName = oriName;
		this.parentSeq = parentSeq;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getOriName() {
		return oriName;
	}
	public void setOriName(String oriName) {
		this.oriName = oriName;
	}
	public Long getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}

}
