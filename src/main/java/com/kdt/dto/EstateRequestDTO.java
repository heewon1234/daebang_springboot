package com.kdt.dto;

public class EstateRequestDTO {
	private UploadEstateDTO estateDTO;
	private String[] optionList;
	
	public UploadEstateDTO getEstateDTO() {
		return estateDTO;
	}
	public void setEstateDTO(UploadEstateDTO estateDTO) {
		this.estateDTO = estateDTO;
	}
	public String[] getOptionList() {
		return optionList;
	}
	public void setOptionList(String[] optionList) {
		this.optionList = optionList;
	}
	public EstateRequestDTO(UploadEstateDTO estateDTO, String[] optionList) {
		super();
		this.estateDTO = estateDTO;
		this.optionList = optionList;
	}
	public EstateRequestDTO() {
		super();
	}
	
	
}