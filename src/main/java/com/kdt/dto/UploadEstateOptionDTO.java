package com.kdt.dto;

public class UploadEstateOptionDTO {
	private Long seq;
	private Long estateCode;
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
	public UploadEstateOptionDTO(Long seq, Long estateCode, String optionCode) {
		super();
		this.seq = seq;
		this.estateCode = estateCode;
		this.optionCode = optionCode;
	}
	public UploadEstateOptionDTO() {
		super();
	}
	
}
