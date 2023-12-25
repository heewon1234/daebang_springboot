package com.kdt.dto;

public class ReportContentsDTO {
	private String id;
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
	public ReportContentsDTO(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public ReportContentsDTO() {
		super();
	}
	
}
