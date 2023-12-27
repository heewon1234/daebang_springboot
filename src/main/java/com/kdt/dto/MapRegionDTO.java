package com.kdt.dto;

public class MapRegionDTO {
	private int seq;
    private String sido;
    private String sigungu;
    private String eup_myeon_dong_gu;
    private String eup_myeon_re_dong;
    private String re;
    private String latitude;
    private String longitude;
    
	public MapRegionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MapRegionDTO(int seq, String sido, String sigungu, String eup_myeon_dong_gu, String eup_myeon_re_dong,
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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
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
