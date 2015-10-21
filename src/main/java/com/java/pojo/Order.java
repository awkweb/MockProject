package com.java.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="order_id")
	private String orderId;

	@Column(name="acc_type")
	private String accType;

	@Column(name="alloc_qty")
	private int allocQty;

	@Column(name="limit_price")
	private float limitPrice;

	private String notes;

	@Column(name="open_qty")
	private int openQty;

	private int orderid;

	private String ordertype;

	private String qualifiers;

	private String side;

	private String status;

	@Column(name="stop_price")
	private float stopPrice;

	private Timestamp timestamp;

	@Column(name="total_qty")
	private int totalQty;

	//bi-directional many-to-one association to Executeblock
	@OneToMany(mappedBy="order")
	private List<Executeblock> executeblocks;

	//bi-directional many-to-one association to Block
	@ManyToOne
	@JoinColumn(name="blockid")
	private Block block;

	//bi-directional one-to-one association to Order
	@OneToOne
	@JoinColumn(name="order_id")
	private Order order1;

	//bi-directional one-to-one association to Order
	@OneToOne(mappedBy="order1")
	private Order order2;

	//bi-directional many-to-one association to Portfolio
	@ManyToOne
	@JoinColumn(name="portfolioid")
	private Portfolio portfolio;

	//bi-directional many-to-one association to Security
	@ManyToOne
	@JoinColumn(name="symbol")
	private Security security;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="pmid")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="traderid")
	private User user2;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="order")
	private List<Position> positions;

	public Order() {
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAccType() {
		return this.accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public int getAllocQty() {
		return this.allocQty;
	}

	public void setAllocQty(int allocQty) {
		this.allocQty = allocQty;
	}

	public float getLimitPrice() {
		return this.limitPrice;
	}

	public void setLimitPrice(float limitPrice) {
		this.limitPrice = limitPrice;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getOpenQty() {
		return this.openQty;
	}

	public void setOpenQty(int openQty) {
		this.openQty = openQty;
	}

	public int getOrderid() {
		return this.orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public String getOrdertype() {
		return this.ordertype;
	}

	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}

	public String getQualifiers() {
		return this.qualifiers;
	}

	public void setQualifiers(String qualifiers) {
		this.qualifiers = qualifiers;
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

	public Security getSecurity() {
		return this.security;
	}

	public void setSecurity(Security security) {
		this.security = security;
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

	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}

	public List<Executeblock> getExecuteblocks() {
		return this.executeblocks;
	}

	public void setExecuteblocks(List<Executeblock> executeblocks) {
		this.executeblocks = executeblocks;
	}

	public Executeblock addExecuteblock(Executeblock executeblock) {
		getExecuteblocks().add(executeblock);
		executeblock.setOrder(this);

		return executeblock;
	}

	public Executeblock removeExecuteblock(Executeblock executeblock) {
		getExecuteblocks().remove(executeblock);
		executeblock.setOrder(null);

		return executeblock;
	}

	public Block getBlock() {
		return this.block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Order getOrder1() {
		return this.order1;
	}

	public void setOrder1(Order order1) {
		this.order1 = order1;
	}

	public Order getOrder2() {
		return this.order2;
	}

	public void setOrder2(Order order2) {
		this.order2 = order2;
	}

	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setOrder(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setOrder(null);

		return position;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", side=" + side + "totalQty=" + totalQty + ", Block ="+block+"]";
	}

	@Transient
	String portId2;

	public String getPortId2() {
		return portId2;
	}

	public void setPortId2(String portId2) {
		this.portId2 = portId2;
	}

	@Transient
	String symbol2;

	public String getSymbol2() {
		return symbol2;
	}

	public void setSymbol2(String symbol2) {
		this.symbol2 = symbol2;
	}

	@Transient
	String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Transient
	String traderId2;

	public String getTraderId2() {
		return traderId2;
	}

	public void setTraderId2(String traderId2) {
		this.traderId2 = traderId2;
	}

}