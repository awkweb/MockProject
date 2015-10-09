package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the execute_blocks database table.
 * 
 */
@Entity
@Table(name="execute_blocks")
@NamedQuery(name="ExecuteBlock.findAll", query="SELECT e FROM ExecuteBlock e")
public class ExecuteBlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="execution_id")
	private int executionId;

	@Column(name="allocated_qty")
	private int allocatedQty;

	@Column(name="block_id")
	private String blockId;

	@Column(name="executed_qty")
	private int executedQty;

	@Column(name="order_id")
	private String orderId;

	private String status;

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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTransactionTime() {
		return this.transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

}