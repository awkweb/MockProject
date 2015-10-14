package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the testorder database table.
 * 
 */
@Entity
@NamedQuery(name="Testorder.findAll", query="SELECT t FROM Testorder t")
public class Testorder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="order_id")
	private int orderId;

	private String status;

	private String symbol;

	public Testorder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}