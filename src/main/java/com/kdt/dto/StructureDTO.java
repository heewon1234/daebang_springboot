package com.kdt.dto;

public class StructureDTO {

	private String structureId;
	private String structureType;
	
	public String getStructureId() {
		return structureId;
	}
	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}
	public String getStructureType() {
		return structureType;
	}
	public void setStructureType(String structureType) {
		this.structureType = structureType;
	}
	public StructureDTO(String structureId, String structureType) {
		super();
		this.structureId = structureId;
		this.structureType = structureType;
	}
	public StructureDTO() {
		super();
	}

}
