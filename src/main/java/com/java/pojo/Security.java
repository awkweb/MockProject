package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the securities database table.
 * 
 */
@Entity
@Table(name="securities")
@NamedQuery(name="Security.findAll", query="SELECT s FROM Security s")
public class Security implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String symbol;

	@Column(name="closing_price")
	private float closingPrice;

	@Column(name="market_price")
	private float marketPrice;

	private String name;

	//bi-directional many-to-one association to Executeblock
	@OneToMany(mappedBy="security")
	private List<Executeblock> executeblocks;

	public Security() {
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getClosingPrice() {
		return this.closingPrice;
	}

	public void setClosingPrice(float closingPrice) {
		this.closingPrice = closingPrice;
	}

	public float getMarketPrice() {
		return this.marketPrice;
	}

	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Executeblock> getExecuteblocks() {
		return this.executeblocks;
	}

	public void setExecuteblocks(List<Executeblock> executeblocks) {
		this.executeblocks = executeblocks;
	}

	public Executeblock addExecuteblock(Executeblock executeblock) {
		getExecuteblocks().add(executeblock);
		executeblock.setSecurity(this);

		return executeblock;
	}

	public Executeblock removeExecuteblock(Executeblock executeblock) {
		getExecuteblocks().remove(executeblock);
		executeblock.setSecurity(null);

		return executeblock;
	}

}