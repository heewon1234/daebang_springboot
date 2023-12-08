package com.kdt.domain.entities;

import java.sql.Timestamp;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="estate_option")
public class EstateOption {
	
	@Id
	@Column(name="seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name="estate_code")
	private Long estateCode;
	
	@Column(name="option_code")
	private String optionCode;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Long getEstateCode() {
		return estateCode;
	}

	public void setEstateCode(Long estateCode) {
		this.estateCode = estateCode;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}

	public EstateOption(Long seq, Long estateCode, String optionCode) {
		super();
		this.seq = seq;
		this.estateCode = estateCode;
		this.optionCode = optionCode;
	}

	public EstateOption() {
		super();
	}
	
	
}
