package com.kdt.dto;

import java.time.Instant;

public class ReplyDTO {
	private Long seq;
	private String writer;
	private String contents;
	private Long parentSeq;
	private Instant writeDate;
	public ReplyDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReplyDTO(Long seq, String writer, String contents, Long parentSeq, Instant writeDate) {
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
	public Instant getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Instant writeDate) {
		this.writeDate = writeDate;
	}
}
