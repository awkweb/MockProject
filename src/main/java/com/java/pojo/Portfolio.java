package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the portfolios database table.
 * 
 */
@Entity
@Table(name="portfolios")
@NamedQuery(name="Portfolio.findAll", query="SELECT p FROM Portfolio p")
public class Portfolio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="port_id")
	private String portId;

	private String name;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="portfolio")
	private List<Order> orders;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="pm_id")
	private User user;

	//bi-directional many-to-one association to Position
	@OneToMany(mappedBy="portfolio")
	private List<Position> positions;

	public Portfolio() {
	}

	public String getPortId() {
		return this.portId;
	}

	public void setPortId(String portId) {
		this.portId = portId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setPortfolio(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setPortfolio(null);

		return order;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public Position addPosition(Position position) {
		getPositions().add(position);
		position.setPortfolio(this);

		return position;
	}

	public Position removePosition(Position position) {
		getPositions().remove(position);
		position.setPortfolio(null);

		return position;
	}

}