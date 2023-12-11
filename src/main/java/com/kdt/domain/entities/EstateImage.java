package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Estate_Image")
public class EstateImage {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="ori_name")
	private String oriName;
	
	@Column(name="sys_name")
	private String sysName;
	
	@Column(name="parent_id")
	private Long parentId;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public EstateImage(Long seq, String oriName, String sysName, Long parentId) {
		super();
		this.seq = seq;
		this.oriName = oriName;
		this.sysName = sysName;
		this.parentId = parentId;
	}

	public EstateImage() {
		super();
	}
	
	
}
