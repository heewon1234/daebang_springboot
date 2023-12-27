package com.kdt.dto;

import com.kdt.domain.entities.Estate;

public class RealEstateViewsDTO {
	private Long viewId;
	private Long viewCount;
	private Long estateId;
	private Estate estate;
	
	public RealEstateViewsDTO() {
		super();
	}
	public RealEstateViewsDTO(Long viewId, Long viewCount, Long estateId) {
		super();
		this.viewId = viewId;
		this.viewCount = viewCount;
		this.estateId = estate.getEstateId();
	}
	public Long getViewId() {
		return viewId;
	}
	public void setViewId(Long viewId) {
		this.viewId = viewId;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public Long getEstateId() {
		return estateId;
	}
	public void setEstateId(Long estateId) {
		this.estateId = estateId;
	}
	public Estate getEstate() {
		return estate;
	}
	public void setEstate(Estate estate) {
		this.estate = estate;
	}
	
}
