package com.ssafy.vue.model;

public class houseFilterDto {
	
	private String dongCode;
	private String dealMonth;
	private String dealYear;
	private String priceStart;
	private String priceEnd;
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}
	public String getDealYear() {
		return dealYear;
	}
	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}
	public String getPriceStart() {
		return priceStart;
	}
	public void setPriceStart(String priceStart) {
		this.priceStart = priceStart;
	}
	public String getPriceEnd() {
		return priceEnd;
	}
	public void setPriceEnd(String priceEnd) {
		this.priceEnd = priceEnd;
	}
	@Override
	public String toString() {
		return "houseFilterDto [dongCode=" + dongCode + ", dealMonth=" + dealMonth + ", dealYear=" + dealYear
				+ ", priceStart=" + priceStart + ", priceEnd=" + priceEnd + "]";
	}
	
	
	
	
	

}
