package com.java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.sun.jmx.snmp.Timestamp;

@Entity
@Table(name="execute_blocks")
@NamedQuery(name="ExecuteBlock.findAll", query="SELECT e FROM ExecuteBlock e")
public class ExecuteBlock  {
//	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="execution_id")
	private int executionId;

	@Column(name="allocated_qty")
	private int allocatedQty;

	@Column(name="average_price")
	private float averagePrice;

	@Column(name="block_id")
	private String blockId;

	@Column(name="executed_qty")
	private int executedQty;

	@Column(name="order_id")
	private String orderId;

	@Column(name="remaining_qty")
	private int remainingQty;

	private String status;

	private String symbol;

	@Column(name="trade_price")
	private float tradePrice;

	@Column(name="transact_fee")
	private float transactFee;

	@Column(name="transaction_time")
	private Timestamp transactionTime;

	public ExecuteBlock() {
	}

	public int getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(int executionId) {
		this.executionId = executionId;
	}

	public int getAllocatedQty() {
		return this.allocatedQty;
	}

	public void setAllocatedQty(int allocatedQty) {
		this.allocatedQty = allocatedQty;
	}

	public float getAveragePrice() {
		return this.averagePrice;
	}

	public void setAveragePrice(float averagePrice) {
		this.averagePrice = averagePrice;
	}

	public String getBlockId() {
		return this.blockId;
	}

	public void setBlockId(String blockId) {
		this.blockId = blockId;
	}

	public int getExecutedQty() {
		return this.executedQty;
	}

	public void setExecutedQty(int executedQty) {
		this.executedQty = executedQty;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getRemainingQty() {
		return this.remainingQty;
	}

	public void setRemainingQty(int remainingQty) {
		this.remainingQty = remainingQty;
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

	public float getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(float tradePrice) {
		this.tradePrice = tradePrice;
	}

	public float getTransactFee() {
		return this.transactFee;
	}

	public void setTransactFee(float transactFee) {
		this.transactFee = transactFee;
	}

	public Timestamp getTransactionTime() {
		return this.transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

}
