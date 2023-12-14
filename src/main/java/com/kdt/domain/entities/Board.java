package com.kdt.domain.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="Board")
public class Board {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="title")
	private String title;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="write_date")
	private Timestamp writeDate;
	
	@Column(name="header")
	private String header;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="view_count")
	private Long viewCount;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="parent_seq")
	private Set<Reply> replies;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="parent_seq")
	private Set<Files> files;
	
	@Transient
	private String favorite;
	
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(Long seq, String boardTitle, String title, String writer, Timestamp writeDate, String header,
			String contents, Long viewCount, String favorite) {
		super();
		this.seq = seq;
		this.boardTitle = boardTitle;
		this.title = title;
		this.writer = writer;
		this.writeDate = writeDate;
		this.header = header;
		this.contents = contents;
		this.viewCount = viewCount;
		this.favorite = favorite;
	}

	public Board(Long seq, String boardTitle, String title, String writer, Timestamp writeDate, String header,
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

	public String getFavorite() {
		return favorite;
	}

	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public Set<Files> getFiles() {
		return files;
	}

	public void setFiles(Set<Files> files) {
		this.files = files;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
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

	public Timestamp getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Timestamp writeDate) {
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