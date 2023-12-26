package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class MyReport {

	@Id
	@Column(name="estate_name")
	private String estateName;
	
	@Column(name="content")
	private String content;
	
	@Column(name="content2")
	private String content2;
	
	@Column(name="status")
	private String status;

	public MyReport() {
		super();
	}

	public MyReport(String estateName, String content, String content2, String status) {
		super();
		this.estateName = estateName;
		this.content = content;
		this.content2 = content2;
		this.status = status;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
