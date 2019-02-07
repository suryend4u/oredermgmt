package com.cc.oms.model;

import java.util.List;

import com.cc.oms.entities.OrderItem;


public class Basket {

	private List<OrderItem> orderItems;
	// private int userId;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

}
