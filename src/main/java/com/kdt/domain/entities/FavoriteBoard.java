package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Favorite_Board")
public class FavoriteBoard {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="board_title")
	private String boardTitle;
	
	@Column(name="id")
	private String id;
	
	@Column(name="parent_seq")
	private Long parentSeq;

	public FavoriteBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FavoriteBoard(Long seq, String boardTitle, String id, Long parentSeq) {
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
