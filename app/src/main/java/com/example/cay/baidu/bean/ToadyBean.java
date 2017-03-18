package com.example.cay.baidu.bean;

public class ToadyBean {
	private String rihan_tv;
	private String usa_tv;
	private String cha_tv;
	private String movie;
	private String date;
	public String getRihan_tv() {
		return rihan_tv;
	}
	public void setRihan_tv(String rihan_tv) {
		this.rihan_tv = rihan_tv;
	}
	public String getUsa_tv() {
		return usa_tv;
	}
	public void setUsa_tv(String usa_tv) {
		this.usa_tv = usa_tv;
	}
	public String getCha_tv() {
		return cha_tv;
	}
	public void setCha_tv(String cha_tv) {
		this.cha_tv = cha_tv;
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ToadyBean{" +
				"rihan_tv='" + rihan_tv + '\'' +
				", usa_tv='" + usa_tv + '\'' +
				", cha_tv='" + cha_tv + '\'' +
				", movie='" + movie + '\'' +
				", date='" + date + '\'' +
				'}';
	}
}
	


