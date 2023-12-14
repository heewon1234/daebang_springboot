package com.kdt.dto;

import java.time.Instant;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public class BoardDTO {
	
	private Long seq;
	private String boardTitle;
	private String title;
	private String writer;
	private Instant writeDate;
	private String header;
	private String contents;
	private Long viewCount;
	private String favorite;
	private Set<ReplyDTO> replies;
	private Set<FilesDTO> files;
	
	public BoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public BoardDTO(Long seq, String boardTitle, String title, String writer, Instant writeDate, String header,
			String contents, Long viewCount) {
		super();
		this.seq = seq;
		this.boardTitle = boardTitle;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.header = header;
		this.contents = contents;
		this.viewCount = viewCount;
	}

	public BoardDTO(String boardTitle) {
		super();
		this.boardTitle = boardTitle;
	}

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}


	public Set<FilesDTO> getFiles() {
		return files;
	}
	public void setFiles(Set<FilesDTO> files) {
		this.files = files;
	}
	public Set<ReplyDTO> getReplies() {
		return replies;
	}
	public void setReplies(Set<ReplyDTO> replies) {
		this.replies = replies;
	}
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Instant getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Instant writeDate) {
		this.writeDate = writeDate;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	
}