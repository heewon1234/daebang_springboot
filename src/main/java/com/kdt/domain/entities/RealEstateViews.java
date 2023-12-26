package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Real_Estate_Views")
public class RealEstateViews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "view_id")
	private int viewId;

	@Column(name = "view_count")
	private String viewCount;
	
	@ManyToOne
	@JoinColumn(name = "estate_id")
	private Estate estate;

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

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public RealEstateViews(int viewId, String viewCount, Estate estate) {
		super();
		this.viewId = viewId;
		this.viewCount = viewCount;
		this.estate = estate;
	}

	public RealEstateViews() {
		super();
	}
	
}
