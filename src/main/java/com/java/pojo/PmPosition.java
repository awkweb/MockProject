package com.java.pojo;

public class PmPosition {
	
	public Object portfolioname;
	public Object security;
	public Object qty;
	public Object avgprice;
	public String getPortfolioname() {
		return (String) portfolioname;
	}
	
	public void setPortfolioname(Object portfolioname) {
		this.portfolioname = portfolioname;
	}
	
	public String getSecurity() {
		return (String) security;
	}
	
	public void setSecurity(Object security) {
		this.security = security;
	}
	
	public Long getQty() {
		return (Long) qty;
	}
	
	public void setQty(Object qty) {
		this.qty = qty;
	}
	
	public Double getAvgprice() {
		return (Double) avgprice;
	}
	
	public void setAvgprice(Object avgprice) {
		this.avgprice = avgprice;
	}
	
	@Override
	public String toString() {
		return " \nportfolioname    " + portfolioname +    ", security    "
				+ security + " qty   " + qty + ", avgprice" + avgprice + "           ";
	}

}
