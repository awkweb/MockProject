package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the executed_block database table.
 * 
 */
@Entity
@Table(name="executed_block")
@NamedQuery(name="ExecutedBlock.findAll", query="SELECT e FROM ExecutedBlock e")
public class ExecutedBlock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="execution_id")
	private int executionId;

	@Column(name="allocated_qty")
	private int allocatedQty;

	@Column(name="avg_price")
	private float avgPrice;

	@Column(name="block_id")
	private String blockId;

	@Column(name="executed_qty")
	private int executedQty;

	@Column(name="remaining_qty")
	private int remainingQty;

	@Column(name="security_symbol")
	private String securitySymbol;

	private String side;

	private String status;

	@Column(name="trade_price")
	private float tradePrice;

	@Column(name="transact_fee")
	private float transactFee;

	@Column(name="transaction_time")
	private Timestamp transactionTime;

	public ExecutedBlock() {
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

	public float getAvgPrice() {
		return this.avgPrice;
	}

	public void setAvgPrice(float avgPrice) {
		this.avgPrice = avgPrice;
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

	public int getRemainingQty() {
		return this.remainingQty;
	}

	public void setRemainingQty(int remainingQty) {
		this.remainingQty = remainingQty;
	}

	public String getSecuritySymbol() {
		return this.securitySymbol;
	}

	public void setSecuritySymbol(String securitySymbol) {
		this.securitySymbol = securitySymbol;
	}

	public String getSide() {
		return this.side;
	}

	public void setSide(String side) {
		this.side = side;
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

}