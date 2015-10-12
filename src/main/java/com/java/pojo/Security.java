
package com.java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="securities")
public class Security {
	@Column
	@Id
	private String symbol;
	@Column
	private String name;
	@Column
	private float market_price;
	@Column
	private float closing_price;
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMarket_price() {
		return market_price;
	}
	public void setMarket_price(float market_price) {
		this.market_price = market_price;
	}
	public float getClosing_price() {
		return closing_price;
	}
	public void setClosing_price(float closing_price) {
		this.closing_price = closing_price;
	}
	@Override
	public String toString() {
		return "Securities [symbol=" + symbol + ", name=" + name
				+ ", market_price=" + market_price + ", closing_price="
				+ closing_price + "]";
	}
	
	
}
	