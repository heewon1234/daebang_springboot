package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Structure")
public class Structure {
	
	@Id
	@Column(name="structure_id")
	private String structureId;
	
	@Column(name="structure_type")
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

	public Structure(String structureId, String structureType) {
		super();
		this.structureId = structureId;
		this.structureType = structureType;
	}

	public Structure() {
		super();
	}
}
