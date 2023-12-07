package com.kdt.dto;

public class EstateRequestDTO {
	private EstateDTO estateDTO;
	private String[] optionCodeList;
	
	public EstateDTO getEstateDTO() {
		return estateDTO;
	}
	public void setEstateDTO(EstateDTO estateDTO) {
		this.estateDTO = estateDTO;
	}
	public String[] getOptionCodeList() {
		return optionCodeList;
	}
	public void setOptionCodeList(String[] optionCodeList) {
		this.optionCodeList = optionCodeList;
	}
	public EstateRequestDTO(EstateDTO estateDTO, String[] optionCodeList) {
		super();
		this.estateDTO = estateDTO;
		this.optionCodeList = optionCodeList;
	}
	public EstateRequestDTO() {
		super();
	}
	
}
