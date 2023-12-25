package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Report_Contents")
public class ReportContents {
	@Id
    @Column(name = "id")
    private String id;

    @Column(name = "content")
    private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ReportContents(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public ReportContents() {
		super();
	}
    
}
