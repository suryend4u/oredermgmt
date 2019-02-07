package com.cc.oms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cc.oms.entities.CustOrder;
import com.cc.oms.entities.OrderItem;
import com.cc.oms.model.Basket;
import com.cc.oms.repo.OrderRepository;

public class ShoppingServiceImpl implements ShoppingService {

	private static final String ORDER_STATUS_SUCCESS = "SUCCESS";
	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional
	public void createOrder(Basket basket, int userId) {

		CustOrder custOrder = new CustOrder();
		custOrder.setUserId(userId);
		custOrder.setOrderstatus(ORDER_STATUS_SUCCESS);
		custOrder.setOrderDate(new Date());
		if (null != basket && null != basket.getOrderItems()) {
			List<OrderItem> itemsInBasket = basket.getOrderItems();
			for (OrderItem orderItem : itemsInBasket) {
				orderItem.setCustOrder(custOrder);
			}
			custOrder.setOrderItems(itemsInBasket);
		}

		orderRepository.save(custOrder);

	}

	@Override
	public List<CustOrder> getAllOrdersForUser(int userId) {
		List<CustOrder> ordersByUser = new ArrayList<>();
		orderRepository.findByUserId(userId).forEach(ordersByUser::add);
		return ordersByUser;
	}

	@Override
	public CustOrder getOrderById(int orderId) {
		return orderRepository.findById(orderId).get();
	}

}
