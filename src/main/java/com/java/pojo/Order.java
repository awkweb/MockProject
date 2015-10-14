package com.java.pojo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@Column(name="order_id")
	private String orderId;
	@Column(name="symbol")
	private String symbol;
	@Column(name="total_qty")
	private int totalQty;
	@Column(name="limit_price")
	private float limitPrice;
	@Column(name="stop_price")
	private float stopPrice;
	@Column(name="side")
	private String side;
	@Column(name="open_qty")
	private int openQty;
	@Column(name="alloc_qty")
	private int allocQty;
	@Column(name="status")
	private String status;
	@Column(name="timestamp")
	private Timestamp timestamp;
	@Column(name="ordertype")
	private String ordertype;
	@Column(name="pmid")
	private String pmId;
	@Column(name="traderid")
	private String traderId;
	@Column(name="blockid")
	private String blockId;
	@Column(name="notes")
	private String notes;
	@Column(name="qualifiers")
	private String qualifiers;
	@Column(name="acc_type")
	private String accType;
	@Column(name="portfolioid")
	private String portfolioId;

	public Order() {
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", symbol=" + symbol + ", totalQty=" + totalQty + ", limitPrice="
				+ limitPrice + ", stopPrice=" + stopPrice + ", side=" + side + ", openQty=" + openQty + ", allocQty="
				+ allocQty + ", status=" + status + ", timestamp=" + timestamp + ", ordertype=" + ordertype + ", pmId="
				+ pmId + ", traderId=" + traderId + ", blockId=" + blockId + ", notes=" + notes + ", qualifiers="
				+ qualifiers + ", accType=" + accType + ", portfolioId=" + portfolioId + "]";
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public float getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public float getStopPrice() {
		return stopPrice;
	}

	public void setStopPrice(float stopPrice) {
		this.stopPrice = stopPrice;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public int getOpenQty() {
		return openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public int getAllocQty() {
		return allocQty;
	}

	public void setAllocQty(int allocQty) {
		this.allocQty = allocQty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getBlockId() {
		return blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getQualifiers() {
		return qualifiers;
	}

	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(String portfolioId) {
		this.portfolioId = portfolioId;
	}

}
