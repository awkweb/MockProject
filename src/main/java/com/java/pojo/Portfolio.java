package com.java.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="portfolios")
public class Portfolio {
	@Id
	@Column(name="port_id")
	private String port_id;
	@Column(name="name")
	private String name;
	@Column(name="pm_id")
	private String pm_id;
	
	public Portfolio() {
		
	}

	public Portfolio(String port_id, String name, String pm_id) {
		super();
		this.port_id = port_id;
		this.name = name;
		this.pm_id = pm_id;
	}

	public String getPort_id() {
		return port_id;
	}

	public void setPort_id(String port_id) {
		this.port_id = port_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPm_id() {
		return pm_id;
	}

	public void setPm_id(String pm_id) {
		this.pm_id = pm_id;
	}

	@Override
	public String toString() {
		return "Portfolios [port_id=" + port_id + ", name=" + name + ", pm_id="
				+ pm_id + "]";
	}
	
	

}
