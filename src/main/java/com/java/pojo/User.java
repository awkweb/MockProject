package com.java.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private String userId;

	private String email;

	@Column(name="f_name")
	private String fName;

	@Column(name="l_name")
	private String lName;

	private String password;

	private String role;

	private String username;

	//bi-directional many-to-one association to Block
	@OneToMany(mappedBy="user")
	private List<Block> blocks;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user1")
	private List<Order> orders1;

	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="user2")
	private List<Order> orders2;

	//bi-directional many-to-one association to Portfolio
	@OneToMany(mappedBy="user")
	private List<Portfolio> portfolios;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFName() {
		return this.fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return this.lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Block> getBlocks() {
		return this.blocks;
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
	}

	public Block addBlock(Block block) {
		getBlocks().add(block);
		block.setUser(this);

		return block;
	}

	public Block removeBlock(Block block) {
		getBlocks().remove(block);
		block.setUser(null);

		return block;
	}

	public List<Order> getOrders1() {
		return this.orders1;
	}

	public void setOrders1(List<Order> orders1) {
		this.orders1 = orders1;
	}

	public Order addOrders1(Order orders1) {
		getOrders1().add(orders1);
		orders1.setUser1(this);

		return orders1;
	}

	public Order removeOrders1(Order orders1) {
		getOrders1().remove(orders1);
		orders1.setUser1(null);

		return orders1;
	}

	public List<Order> getOrders2() {
		return this.orders2;
	}

	public void setOrders2(List<Order> orders2) {
		this.orders2 = orders2;
	}

	public Order addOrders2(Order orders2) {
		getOrders2().add(orders2);
		orders2.setUser2(this);

		return orders2;
	}

	public Order removeOrders2(Order orders2) {
		getOrders2().remove(orders2);
		orders2.setUser2(null);

		return orders2;
	}

	public List<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public Portfolio addPortfolio(Portfolio portfolio) {
		getPortfolios().add(portfolio);
		portfolio.setUser(this);

		return portfolio;
	}

	public Portfolio removePortfolio(Portfolio portfolio) {
		getPortfolios().remove(portfolio);
		portfolio.setUser(null);

		return portfolio;
	}

}