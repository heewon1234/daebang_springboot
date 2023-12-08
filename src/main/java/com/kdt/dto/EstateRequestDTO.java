package com.kdt.dto;

public class EstateRequestDTO {
	private EstateDTO estateDTO;
	private String[] optionList;
	
	public EstateDTO getEstateDTO() {
		return estateDTO;
	}
	public void setEstateDTO(EstateDTO estateDTO) {
		this.estateDTO = estateDTO;
	}
	public String[] getOptionList() {
		return optionList;
	}
	public void setOptionList(String[] optionList) {
		this.optionList = optionList;
	}
	public EstateRequestDTO(EstateDTO estateDTO, String[] optionList) {
		super();
		this.estateDTO = estateDTO;
		this.optionList = optionList;
	}
	public EstateRequestDTO() {
		super();
	}
	
	
}