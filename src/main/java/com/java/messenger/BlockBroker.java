package com.java.messenger;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = {"blockId", "executedQty", "limitPrice", "openQty", 
		"status", "stopPrice", "timestamp", "totalQty", "side", "symbol"})
@XmlRootElement( name = "Block")
public class BlockBroker {
	// private static final long serialVersionUID = 1L;

	private String blockId;

	private int executedQty;

	private float limitPrice;

	private int openQty;

	private String status;

	private float stopPrice;

	private Date timestamp;

	private int totalQty;

	private String side;

	private String symbol;

	public BlockBroker() {
		// default constructor
	}

	/**
	 * Added extended Constructor for testing purposes
	 * @param blockId
	 * @param executedQty
	 * @param limitPrice
	 * @param openQty
	 * @param status
	 * @param stopPrice
	 * @param timestamp
	 * @param totalQty
	 */
	public BlockBroker(String blockId, int executedQty, float limitPrice, int openQty, String status, float stopPrice,
			Date timestamp, int totalQty) {
		super();
		this.blockId = blockId;
		this.executedQty = executedQty;
		this.limitPrice = limitPrice;
		this.openQty = openQty;
		this.status = status;
		this.stopPrice = stopPrice;
		this.timestamp = timestamp;
		this.totalQty = totalQty;
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

	public Date getTimestamp() {
		return this.timestamp;
	}

	@XmlElement(name = "Timestamp")
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getTotalQty() {
		return this.totalQty;
	}

	@XmlElement(name = "Total-Quantity")
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public String getSide() {
		return side;
	}

	@XmlElement(name = "Side")
	public void setSide(String side) {
		this.side = side;
	}

	public String getSymbol() {
		return symbol;
	}

	@XmlElement(name = "Symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", executedQty=" + executedQty
				+ ", limitPrice=" + limitPrice + ", openQty=" + openQty
				+ ", status=" + status + ", stopPrice=" + stopPrice
				+ ", timestamp=" + timestamp + ", totalQty=" + totalQty
				+ ", side=" + side + ", symbol=" + symbol + "]";
	}

}
