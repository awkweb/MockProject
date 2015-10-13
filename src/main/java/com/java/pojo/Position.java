package com.java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="positions")
public class Position {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="position_id")
	private String position_id;
	@Column(name="order_id")
	private String order_id;
	@Column(name="port_id")
	private String portfolio_id;
	
	
	public Position(){
		
	}


	public Position(String position_id, String order_id, String portfolio_id) {
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


	public String getPosition_id() {
		return position_id;
	}


	public void setPosition_id(String position_id) {
		this.position_id = position_id;
	}


	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}


	public String getPortfolio_id() {
		return portfolio_id;
	}


	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}
	
	

}
