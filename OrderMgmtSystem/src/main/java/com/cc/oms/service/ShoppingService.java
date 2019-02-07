package com.cc.oms.service;

import java.util.List;

import com.cc.oms.entities.CustOrder;
import com.cc.oms.model.Basket;

public interface ShoppingService {

	public void createOrder(Basket basket, int userId);

	public List<CustOrder> getAllOrdersForUser(int userId);

	public CustOrder getOrderById(int orderId);
}
