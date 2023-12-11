package com.kdt.dto;

public class FilesDTO {

	private Long seq;
	private String sysName;
	private String oriName;
	private Long parentSeq;
	private String type;
	public FilesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FilesDTO(Long seq, String sysName, String oriName, Long parentSeq, String type) {
		super();
		this.seq = seq;
		this.sysName = sysName;
		this.oriName = oriName;
		this.parentSeq = parentSeq;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
