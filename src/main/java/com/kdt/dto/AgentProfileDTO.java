package com.kdt.dto;

public class AgentProfileDTO {
	private Long seq;
	private String oriName;
	private String sysName;
	private String parentEmail;
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
	public String getParentEmail() {
		return parentEmail;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	public AgentProfileDTO(Long seq, String oriName, String sysName, String parentEmail) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentEmail = parentEmail;
	}
	public AgentProfileDTO() {
		super();
	}
	
}
