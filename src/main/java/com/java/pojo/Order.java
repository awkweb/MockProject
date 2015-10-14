package com.java.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private String orderId;

	@Column(name="alloc_qty")
	private int allocQty;

	@Column(name="blockid")
	private String blockId;

	@Column(name="limit_price")
	private float limitPrice;

	@Column(name="notes")
	private String notes;

	@Column(name="open_qty")
	private int openQty;

	@Column(name="ordertype")
	private String ordertype;

	@Column(name="pmid")
	private String pmId;

	@Column(name="side")
	private String side;

	@Column(name="status")
	private String status;

	@Column(name="stop_price")
	private float stopPrice;

	@Column(name="symbol")
	private String symbol;

	@Column(name="timestamp")
	private Timestamp timestamp;

	@Column(name="total_qty")
	private int totalQty;

	@Column(name="traderid")
	private String traderId;
	
	@Column(name="qualifiers")
	private String qualifier;
	
	@Column(name="acc_type")
	private String accountType;
	
	@Column(name="portfolioid")
	private String portId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getAllocQty() {
		return allocQty;
	}

	public void setAllocQty(int allocQty) {
		this.allocQty = allocQty;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public float getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getOpenQty() {
		return openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public String getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getPmId() {
		return pmId;
	}

	public void setPmId(String pmId) {
		this.pmId = pmId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(float stopPrice) {
		this.stopPrice = stopPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPortId() {
		return portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", allocQty=" + allocQty
				+ ", blockId=" + blockId + ", limitPrice=" + limitPrice
				+ ", notes=" + notes + ", openQty=" + openQty + ", ordertype="
				+ ordertype + ", pmId=" + pmId + ", side=" + side + ", status="
				+ status + ", stopPrice=" + stopPrice + ", symbol=" + symbol
				+ ", timestamp=" + timestamp + ", totalQty=" + totalQty
				+ ", traderId=" + traderId + ", qualifier=" + qualifier
				+ ", accountType=" + accountType + ", portId=" + portId + "]";
	}

	

	
}
