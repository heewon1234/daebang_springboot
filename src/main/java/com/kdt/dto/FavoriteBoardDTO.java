package com.kdt.dto;

public class FavoriteBoardDTO {

	private Long seq;
	private String boardTitle;
	private String id;
	private Long parentSeq;
	public FavoriteBoardDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavoriteBoardDTO(Long seq, String boardTitle, String id, Long parentSeq) {
		super();
		this.seq = seq;
		this.boardTitle = boardTitle;
		this.id = id;
		this.parentSeq = parentSeq;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getParentSeq() {
		return parentSeq;
	}
	public void setParentSeq(Long parentSeq) {
		this.parentSeq = parentSeq;
	}

}
