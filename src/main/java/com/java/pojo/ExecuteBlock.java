package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the executeblocks database table.
 * 
 */
@Entity
@Table(name="executeblocks")
@NamedQuery(name="Executeblock.findAll", query="SELECT e FROM ExecuteBlock e")
public class ExecuteBlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="execution_id")
	private String executionId;

	@Column(name="allocated_qty")
	private int allocatedQty;

	@Column(name="average_price")
	private float averagePrice;

	@Column(name="executed_qty")
	private int executedQty;

	@Column(name="order_id")
	private String orderId;

	@Column(name="remaining_qty")
	private int remainingQty;

	private String status;

	@Column(name="trade_price")
	private float tradePrice;

	@Column(name="transact_fee")
	private float transactFee;

	@Column(name="transaction_time")
	private Timestamp transactionTime;

	//bi-directional many-to-one association to Block
	@ManyToOne
	@JoinColumn(name="blockid")
	private Block block;

	//bi-directional many-to-one association to Order
	@ManyToOne
	@JoinColumn(name="orderid")
	private Order order;

	//bi-directional many-to-one association to Security
	@ManyToOne
	@JoinColumn(name="symbol")
	private Security security;

	public ExecuteBlock() {
	}

	public String getExecutionId() {
		return this.executionId;
	}

	public void setExecutionId(String executionId) {
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

<<<<<<< HEAD

=======
>>>>>>> 15c622984a11e3827ce883928fd391da0ec711fe
	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Security getSecurity() {
		return this.security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

<<<<<<< HEAD
}

=======
}
>>>>>>> 15c622984a11e3827ce883928fd391da0ec711fe
