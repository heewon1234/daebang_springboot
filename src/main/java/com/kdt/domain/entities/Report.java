package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Report")
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@Column(name = "writer")
	private String writer;

	//상세 내용
	@Column(name = "content")
	private String content;

	@Column(name = "write_date")
	private Timestamp writeDate;

	//매물관련
	@ManyToOne
	@JoinColumn(name = "estate_id")
	private Estate estate;


	//중개사
	@OneToOne
	@JoinColumn(name = "taker", referencedColumnName = "email")
	private RealEstateAgent realEstateAgent;

	//신고 내용
	@OneToOne
	@JoinColumn(name = "contents_code", referencedColumnName = "id")
	private ReportContents reportContents;

	//신고 상태
	@OneToOne
	@JoinColumn(name = "status_code", referencedColumnName = "id")
	private ReportStatus reportStatus;

	public void setTaker(String email) {
	    if (this.realEstateAgent == null) {
	        this.realEstateAgent = new RealEstateAgent();
	    }
	    this.realEstateAgent.setEmail(email);
	}
	public void setContentsCode(String id) {
	    if (this.reportContents == null) {
	        this.reportContents = new ReportContents();
	    }
	    this.reportContents.setId(id);
	}
	public void setEstateId(Long estateId) {
	    if (this.estate == null) {
	        this.estate = new Estate();
	    }
	    this.estate.setEstateId(estateId);
	}public void setStatus_code(String id) {
	    if (this.reportStatus == null) {
	        this.reportStatus = new ReportStatus();
	    }
	    this.reportStatus.setId(id);
	}







	public Report(Long seq, String writer, String content, Timestamp writeDate, Estate estate,
			RealEstateAgent realEstateAgent, ReportContents reportContents, ReportStatus reportStatus) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.content = content;
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









	public String getWriter() {
		return writer;
	}









	public void setWriter(String writer) {
		this.writer = writer;
	}









	public String getContent() {
		return content;
	}









	public void setContent(String content) {
		this.content = content;
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









	public Report() {
		super();
	}

}
