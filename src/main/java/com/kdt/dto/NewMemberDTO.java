package com.kdt.dto;

import java.time.LocalDate;

public class NewMemberDTO {
	private Long seq;
	private LocalDate newMemberDate;
	private int newMemberCount;
	


	public NewMemberDTO(Long seq, LocalDate newMemberDate, int newMemberCount) {
		super();
		this.seq = seq;
		this.newMemberDate = newMemberDate;
		this.newMemberCount = newMemberCount;
	}



	public Long getSeq() {
		return seq;
	}



	public void setSeq(Long seq) {
		this.seq = seq;
	}



	public LocalDate getNewMemberDate() {
		return newMemberDate;
	}



	public void setNewMemberDate(LocalDate newMemberDate) {
		this.newMemberDate = newMemberDate;
	}



	public int getNewMemberCount() {
		return newMemberCount;
	}



	public void setNewMemberCount(int newMemberCount) {
		this.newMemberCount = newMemberCount;
	}



	public NewMemberDTO() {
		super();
	}
	
	
}
