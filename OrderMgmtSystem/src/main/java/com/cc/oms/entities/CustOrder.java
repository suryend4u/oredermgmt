package com.cc.oms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the CUST_ORDER database table.
 * 
 */
@Entity
@Table(name="CUST_ORDER")

public class CustOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ORDER_ID")
	private int orderId;

	@Temporal(TemporalType.DATE)
	@Column(name="ORDER_DATE")
	private Date orderDate;

	private String orderstatus;

/*	//bi-directional many-to-one association to User
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name="USERID")
	@JsonIgnoreProperties("custOrders")*/

	@Column(name = "USERID")
	private int userId;

	//bi-directional many-to-one association to OrderItem
	@OneToMany(mappedBy = "custOrder", cascade = { CascadeType.ALL })
	@JsonIgnoreProperties("custOrder")
	private List<OrderItem> orderItems;

	public CustOrder() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

/*	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	public List<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setCustOrder(this);

		return orderItem;
	}

	public OrderItem removeOrderItem(OrderItem orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setCustOrder(null);

		return orderItem;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}