package com.kdt.dto;

public class ReportInsertDTO {
	private String writer;
    private String contentsCode;
    private String taker;
    private Long estateId;
    private String content;
    
	public ReportInsertDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportInsertDTO(String writer, String contentsCode, String taker, Long estateId, String content) {
		super();
		this.writer = writer;
		this.contentsCode = contentsCode;
		this.taker = taker;
		this.estateId = estateId;
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContentsCode() {
		return contentsCode;
	}

	public void setContentsCode(String contentsCode) {
		this.contentsCode = contentsCode;
	}

	public String getTaker() {
		return taker;
	}

	public void setTaker(String taker) {
		this.taker = taker;
	}

	public Long getEstateId() {
		return estateId;
	}

	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    

}
