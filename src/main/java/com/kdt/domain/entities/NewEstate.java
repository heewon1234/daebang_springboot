package com.kdt.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewEstate {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq")
	private Long seq;
	@Column(name = "estate_date", unique = true)
	private LocalDate estateDate;
	@Column(name="estate_count")
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
	public NewEstate(Long seq, LocalDate estateDate, int estateCount) {
		super();
		this.seq = seq;
		this.estateDate = estateDate;
		this.estateCount = estateCount;
	}
	public NewEstate() {
		super();
	}
	
}
