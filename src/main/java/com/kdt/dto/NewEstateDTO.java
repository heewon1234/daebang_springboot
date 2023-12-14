package com.kdt.dto;

import java.time.LocalDate;

public class NewEstateDTO {
	private Long seq;
	private LocalDate estateDate;
	private int estateCount;
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public LocalDate getEstateDate() {
		return estateDate;
	}
	public void setEstateDate(LocalDate estateDate) {
		this.estateDate = estateDate;
	}
	public int getEstateCount() {
		return estateCount;
	}
	public void setEstateCount(int estateCount) {
		this.estateCount = estateCount;
	}
	public NewEstateDTO(Long seq, LocalDate estateDate, int estateCount) {
		super();
		this.seq = seq;
		this.estateDate = estateDate;
		this.estateCount = estateCount;
	}
	public NewEstateDTO() {
		super();
	}
	
}
