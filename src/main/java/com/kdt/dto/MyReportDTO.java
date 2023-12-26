package com.kdt.dto;

public class MyReportDTO {
	private String estateName;
	private String content;
	private String content2;
	private String status;
	public MyReportDTO() {
		super();
	}
	public MyReportDTO(String estateName, String content, String content2, String status) {
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
