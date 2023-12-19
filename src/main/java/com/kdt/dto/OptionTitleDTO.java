package com.kdt.dto;

public class OptionTitleDTO {
	private String optionId;
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
	public OptionTitleDTO(String optionId, String optionName) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
	}
	public OptionTitleDTO() {
		super();
	}

}
