package com.java.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProposedBlock {
	private String side;
	private String symbol;
	private Float limit;
	private Float stop;
	private int totalQty;
	private List<Order> orders;

	
	
	public ProposedBlock(String side, String symbol, Float limit, Float stop, int totalQty) {
		super();
		this.side = side;
		this.symbol = symbol;
		this.limit = limit;
		this.stop = stop;
		this.totalQty = totalQty;
		this.orders = new ArrayList<Order>();
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}


	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Float getLimit() {
		return limit;
	}
	public void setLimit(Float limit) {
		this.limit = limit;
	}
	public Float getStop() {
		return stop;
	}
	public void setStop(Float stop) {
		this.stop = stop;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	@Override
	public String toString() {
		return "ProposedBlock [side=" + side + ", symbol=" + symbol + ", limit=" + limit + ", stop=" + stop
				+ ", totalQty=" + totalQty + "]";
	}
	
	
}
