package com.java.pojo;

public class ExecBlock {
Object side;
Object symbol;
Object status;
Object Trasactiontime;
Object trasacprice;
Object execid;
Object blockid;
Object qty;
public String getSide() {
	return (String) side;
}
public void setSide(Object side) {
	this.side = side;
}
public String getSymbol() {
	return (String) symbol;
}
public void setSymbol(Object symbol) {
	this.symbol = symbol;
}
public String getStatus() {
	return (String) status;
}
public void setStatus(Object status) {
	this.status = status;
}
public String getTrasactiontime() {
	return (String) Trasactiontime;
}
public void setTrasactiontime(Object trasactiontime) {
	Trasactiontime = trasactiontime;
}
public Float getTrasacprice() {
	return (Float) trasacprice;
}
public void setTrasacprice(Object trasacprice) {
	this.trasacprice = trasacprice;
}
public Integer getExecid() {
	return (Integer) execid;
}
public void setExecid(Object execid) {
	this.execid = execid;
}
public String getBlockid() {
	return (String) blockid;
}
public void setBlockid(Object blockid) {
	this.blockid = blockid;
}
public Integer getQty() {
	return (Integer) qty;
}
public void setQty(Object qty) {
	this.qty = qty;
}



}
