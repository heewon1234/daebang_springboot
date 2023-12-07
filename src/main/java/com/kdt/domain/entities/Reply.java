package com.kdt.domain.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Reply")
public class Reply {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="parent_seq")
	private Long parentSeq;
	
	@Column(name="write_date")
	private Timestamp writeDate;

	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(Long seq, String writer, String contents, Long parentSeq, Timestamp writeDate) {
		super();
		this.seq = seq;
		this.writer = writer;
		this.contents = contents;
		this.parentSeq = parentSeq;
		this.writeDate = writeDate;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Long getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
}
