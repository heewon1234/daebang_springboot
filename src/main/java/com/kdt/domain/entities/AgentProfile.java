package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Agent_Profile")
public class AgentProfile {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="ori_name")
	private String oriName;
	
	@Column(name="sys_name")
	private String sysName;
	
	@Column(name="parent_email")
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

	public AgentProfile(Long seq, String oriName, String sysName, String parentEmail) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentEmail = parentEmail;
	}

	public AgentProfile() {
		super();
	}
	
}
