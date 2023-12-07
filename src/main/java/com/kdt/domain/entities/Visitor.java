package com.kdt.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Visitor {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq")
    private Long seq;
	
	@Column(name = "visitor_date", unique = true)
	private LocalDate visitorDate;
	
	@Column(name="visitor_count")
    private int visitorCount;


	public Visitor(Long seq, LocalDate visitorDate, int visitorCount) {
		super();
		this.seq = seq;
		this.visitorDate = visitorDate;
		this.visitorCount = visitorCount;
	}


	public Long getSeq() {
		return seq;
	}


	public void setSeq(Long seq) {
		this.seq = seq;
	}


	public LocalDate getVisitorDate() {
		return visitorDate;
	}


	public void setVisitorDate(LocalDate visitorDate) {
		this.visitorDate = visitorDate;
	}


	public int getVisitorCount() {
		return visitorCount;
	}


	public void setVisitorCount(int visitorCount) {
		this.visitorCount = visitorCount;
	}


	public Visitor() {
		super();
    }
}
