package com.rehber.dao;

public class TelefonDomain {

	private int id;
	private String adi;
	private String soyadi;
	private String telefonNo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	

	public String getAdi() {
		return adi;
	}
	public void setAdi(String adi) {
		this.adi = adi;
	}
	public String getSoyadi() {
		return soyadi;
	}
	public void setSoyadi(String soyadi) {
		this.soyadi = soyadi;
	}
	public String getTelefonNo() {
		return telefonNo;
	}
	public void setTelefonNo(String telefonNo) {
		this.telefonNo = telefonNo;
	}	
	
	@Override
	public String toString() {
		return id + " " + adi + " " + soyadi + " " + telefonNo;
	}
}
