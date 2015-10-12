package com.java.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = {"blockId", })
@XmlRootElement( name = "Block")
@Entity
@Table(name="blocks")
@NamedQuery(name="Block.findAll", query="SELECT b FROM Block b")
public class Block {
//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="block_id")
	private String blockId;

	@Column(name="executed_qty")
	private int executedQty;

	@Column(name="limit_price")
	private float limitPrice;

	@Column(name="open_qty")
	private int openQty;

	private String status;

	@Column(name="stop_price")
	private float stopPrice;

	private Timestamp timestamp;

	@Column(name="total_qty")
	private int totalQty;

	public Block() {
	}

	public String getBlockId() {
		return this.blockId;
	}
	@XmlElement(name = "BlockId")
	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public int getExecutedQty() {
		return this.executedQty;
	}
	
	@XmlElement(name = "Executed-Quantity")
	public void setExecutedQty(int executedQty) {
		this.executedQty = executedQty;
	}

	public float getLimitPrice() {
		return this.limitPrice;
	}
	
	@XmlElement(name = "Limit-Price")
	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public int getOpenQty() {
		return this.openQty;
	}
	
	@XmlElement(name = "Open-Quantity")
	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public String getStatus() {
		return this.status;
	}

	@XmlElement(name = "Status")
	public void setStatus(String status) {
		this.status = status;
	}

	public float getStopPrice() {
		return this.stopPrice;
	}
	
	@XmlElement(name = "Stop-Price")
	public void setStopPrice(float stopPrice) {
		this.stopPrice = stopPrice;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}
	
	@XmlElement(name = "Timestamp")
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getTotalQty() {
		return this.totalQty;
	}
	
	@XmlElement(name = "Total-Quantity")
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

}
