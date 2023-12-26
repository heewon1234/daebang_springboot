package com.kdt.dto;

import com.kdt.domain.entities.Estate;

public class RealEstateViewsDTO {
	private int viewId;
	private String viewCount;
	private Long estateId;
	private Estate estate;
	
	public RealEstateViewsDTO() {
		super();
	}
	public RealEstateViewsDTO(int viewId, String viewCount, Long estateId) {
		super();
		this.viewId = viewId;
		this.viewCount = viewCount;
		this.estateId = estate.getEstateId();
	}
	public int getViewId() {
		return viewId;
	}
	public void setViewId(int viewId) {
		this.viewId = viewId;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
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
