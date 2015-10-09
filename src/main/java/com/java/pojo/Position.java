package com.java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="positions")
public class Position {

	@Id
	@Column(name="position_id")
	private int position_id;
	@Column(name="order_id")
	private int order_id;
	@Column(name="port_id")
	private int portfolio_id;

	public Position(){

	}

	public Position(int position_id, int order_id, int portfolio_id) {
		super();
		this.position_id = position_id;
		this.order_id = order_id;
		this.portfolio_id = portfolio_id;
	}

	@Override
	public String toString() {
		return "Positions [position_id=" + position_id + ", order_id=" + order_id + ", portfolio_id=" + portfolio_id
				+ "]";
	}

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(int portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

}
