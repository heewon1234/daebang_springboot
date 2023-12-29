package com.kdt.dto;

import java.sql.Timestamp;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.domain.entities.ReportContents;
import com.kdt.domain.entities.ReportStatus;

public class ReportDTO {
	private Long seq;
	private String content;//상세 내용
	private String writer;//사용자
	private Timestamp writeDate;
	
	private Estate estate;
	private RealEstateAgent realEstateAgent;
	private ReportContents reportContents;
	private ReportStatus reportStatus;
	
	


	public ReportDTO(Long seq, String content, String writer, Timestamp writeDate, Estate estate,
			RealEstateAgent realEstateAgent, ReportContents reportContents, ReportStatus reportStatus) {
		super();
		this.seq = seq;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.estate = estate;
		this.realEstateAgent = realEstateAgent;
		this.reportContents = reportContents;
		this.reportStatus = reportStatus;
	}




	public Long getSeq() {
		return seq;
	}




	public void setSeq(Long seq) {
		this.seq = seq;
	}




	public String getContent() {
		return content;
	}




	public void setContent(String content) {
		this.content = content;
	}




	public String getWriter() {
		return writer;
	}




	public void setWriter(String writer) {
		this.writer = writer;
	}




	public Timestamp getWriteDate() {
		return writeDate;
	}




	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}




	public Estate getEstate() {
		return estate;
	}




	public void setEstate(Estate estate) {
		this.estate = estate;
	}




	public RealEstateAgent getRealEstateAgent() {
		return realEstateAgent;
	}




	public void setRealEstateAgent(RealEstateAgent realEstateAgent) {
		this.realEstateAgent = realEstateAgent;
	}




	public ReportContents getReportContents() {
		return reportContents;
	}




	public void setReportContents(ReportContents reportContents) {
		this.reportContents = reportContents;
	}




	public ReportStatus getReportStatus() {
		return reportStatus;
	}




	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}




	public ReportDTO() {
		super();
	}
	
}
