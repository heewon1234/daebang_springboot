package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Review_Files")
public class ReviewFiles {
	
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

	public ReviewFiles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewFiles(Long seq, String sysName, String oriName, Long parentSeq) {
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
