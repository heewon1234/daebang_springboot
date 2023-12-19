package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Option_Title")
public class OptionTitle {
	
	@Id
	@Column(name="option_id")
	private String optionId;
	
	@Column(name="option_name")
	private String optionName;

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public OptionTitle(String optionId, String optionName) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
	}

	public OptionTitle() {
		super();
	}
	
}
