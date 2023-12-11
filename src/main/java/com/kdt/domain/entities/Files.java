package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Files")
public class Files {

	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long seq;
	
	@Column(name="sys_name")
	private String sysName;
	
	@Column(name="ori_name")
	private String oriName;
	
	@Column(name="parent_seq")
	private Long parentSeq;
	
	@Column(name="type")
	private String type;

	public Files() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Files(Long seq, String sysName, String oriName, Long parentSeq, String type) {
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
