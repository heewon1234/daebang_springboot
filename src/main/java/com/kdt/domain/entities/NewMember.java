package com.kdt.domain.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NewMember {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="seq")
    private Long seq;
	
	@Column(name = "newMember_date", unique = true)
	private LocalDate newMemberDate;
	
	@Column(name="newMember_count")
    private int newMemberCount;
	
	public NewMember(Long seq, LocalDate newMemberDate, int newMemberCount) {
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

	public NewMember() {
		super();
    }
}
