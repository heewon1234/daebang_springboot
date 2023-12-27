package com.kdt.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Map_search_region")
public class MapRegion {

    @Id // 기본 키 필드가 있다고 가정합니다.
    @Column(name="seq")
    private String seq;
    
    @Column(name="sido")
    private String sido;

    @Column(name="sigungu")
    private String sigungu;

    @Column(name="eup_myeon_dong_gu")
    private String eup_myeon_dong_gu;

    @Column(name="eup_myeon_re_dong")
    private String eup_myeon_re_dong;

    @Column(name="re")
    private String re;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

	public MapRegion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapRegion(String seq, String sido, String sigungu, String eup_myeon_dong_gu, String eup_myeon_re_dong,
			String re, String latitude, String longitude) {
		super();
		this.seq = seq;
		this.sido = sido;
		this.sigungu = sigungu;
		this.eup_myeon_dong_gu = eup_myeon_dong_gu;
		this.eup_myeon_re_dong = eup_myeon_re_dong;
		this.re = re;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSido() {
		return sido;
	}

	public void setSido(String sido) {
		this.sido = sido;
	}

	public String getSigungu() {
		return sigungu;
	}

	public void setSigungu(String sigungu) {
		this.sigungu = sigungu;
	}

	public String getEup_myeon_dong_gu() {
		return eup_myeon_dong_gu;
	}

	public void setEup_myeon_dong_gu(String eup_myeon_dong_gu) {
		this.eup_myeon_dong_gu = eup_myeon_dong_gu;
	}

	public String getEup_myeon_re_dong() {
		return eup_myeon_re_dong;
	}

	public void setEup_myeon_re_dong(String eup_myeon_re_dong) {
		this.eup_myeon_re_dong = eup_myeon_re_dong;
	}

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
