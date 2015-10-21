package com.java.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the blocks database table.
 * 
 */
@Entity
@Table(name="blocks")
@NamedQuery(name="Block.findAll", query="SELECT b FROM Block b")
public class Block implements Serializable {
	private static final long serialVersionUID = 1L;

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

	private String side;

	private String status;

	@Column(name="stop_price")
	private float stopPrice;

	private String symbol;

	private Timestamp timestamp;

	@Column(name="total_qty")
	private int totalQty;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="traderid")
	private User user;

	//bi-directional many-to-one association to Executeblock
	@OneToMany(mappedBy="block")
	private List<Executeblock> executeblocks;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="block", fetch=FetchType.EAGER)
	private List<Order> orders;

	public Block() {
	}
	
	public Block(String symbol, String side, String status, User user, List<Order> order, float limit, float stop) {
		this.executedQty = 0;
		this.symbol = symbol;
		this.side = side;
		this.status = status;
		this.totalQty = 0;
		this.user = user;
		this.orders = order;
		this.limitPrice=limit;
		this.stopPrice=stop;
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

	public float getLimitPrice() {
		return this.limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public int getOpenQty() {
		return this.openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
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

	public float getStopPrice() {
		return this.stopPrice;
	}

	public void setStopPrice(float stopPrice) {
		this.stopPrice = stopPrice;
	}

	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getTotalQty() {
		return this.totalQty;
	}
	public int calculateTotalQty(){
		int sum =0;
		for(Order order : this.orders){
			sum+=order.getTotalQty();
		}
		return sum;
	}

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Executeblock> getExecuteblocks() {
		return this.executeblocks;
	}

	public void setExecuteblocks(List<Executeblock> executeblocks) {
		this.executeblocks = executeblocks;
	}

	public Executeblock addExecuteblock(Executeblock executeblock) {
		getExecuteblocks().add(executeblock);
		executeblock.setBlock(this);

		return executeblock;
	}

	public Executeblock removeExecuteblock(Executeblock executeblock) {
		getExecuteblocks().remove(executeblock);
		executeblock.setBlock(null);

		return executeblock;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		this.totalQty+=order.getTotalQty();
		order.setBlock(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setBlock(null);

		return order;
	}

	@Override
	public String toString() {
		return "Block [blockId=" + blockId + ", side=" + side + ", symbol=" + symbol + ", totalQty=" + totalQty
				+ ", orders=" + orders + "]";
	}
	

}