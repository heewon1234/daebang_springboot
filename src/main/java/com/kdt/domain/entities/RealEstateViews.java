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
	private Long viewId;

	@Column(name = "view_count")
	private Long viewCount;
	
	@ManyToOne
	@JoinColumn(name = "estate_id")
	private Estate estate;

	public Long getViewId() {
		return viewId;
	}

	public void setViewId(Long viewId) {
		this.viewId = viewId;
	}

	public Long getViewCount() {
		return viewCount;
	}

	public void setViewCount(long i) {
		this.viewCount = i;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public RealEstateViews(Long viewId, Long viewCount, Estate estate) {
		super();
		this.viewId = viewId;
		this.viewCount = viewCount;
		this.estate = estate;
	}

	public RealEstateViews() {
		super();
	}

	public void setEstateId(Long estateId) {
	    if (this.estate == null) {
	        this.estate = new Estate();
	    }
	    this.estate.setEstateId(estateId);
	}

	
}
