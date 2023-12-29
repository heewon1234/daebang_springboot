package com.kdt.dto;

import java.sql.Timestamp;

import com.kdt.domain.entities.Estate;
import com.kdt.domain.entities.RealEstateAgent;
import com.kdt.domain.entities.ReportContents;
import com.kdt.domain.entities.ReportStatus;

public class ReportDTO {
	private Long seq;
	private String contents_code;
	private String content;//상세 내용
	private String writer;//사용자
	private String taker;//중개사
	private Long estate_id;
	private String status_code;
	private Timestamp writeDate;
	
	private Estate estate;
	private RealEstateAgent realEstateAgent;
	private ReportContents reportContents;
	private ReportStatus reportStatus;
	
	
	public ReportDTO(Long seq, String contents_code, String content, String writer, String taker, Long estate_id,
			String status_code, Timestamp writeDate, Estate estate, RealEstateAgent realEstateAgent,
			ReportContents reportContents, ReportStatus reportStatus) {
		super();
		this.seq = seq;
		this.contents_code = contents_code;
		this.content = content;
		this.writer = writer;
		this.taker = taker;
		this.estate_id = estate_id;
		this.status_code = status_code;
		this.writeDate = writeDate;
		this.estate = estate;
		this.realEstateAgent = realEstateAgent;
		this.reportContents = reportContents;
		this.reportStatus = reportStatus;
	}


	public String getContents_code() {
		return contents_code;
	}


	public void setContents_code(String contents_code) {
		this.contents_code = contents_code;
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


	public String getTaker() {
		return taker;
	}


	public void setTaker(String taker) {
		this.taker = taker;
	}


	public Long getEstate_id() {
		return estate_id;
	}


	public void setEstate_id(Long estate_id) {
		this.estate_id = estate_id;
	}


	public String getStatus_code() {
		return status_code;
	}


	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}


	public Timestamp getWriteDate() {
		return writeDate;
	}


	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}


	


	public Long getSeq() {
		return seq;
	}


	public void setSeq(Long seq) {
		this.seq = seq;
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
