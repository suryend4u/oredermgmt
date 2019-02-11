package com.cc.oms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cc.oms.entities.CustOrder;
import com.cc.oms.entities.Item;
import com.cc.oms.entities.OrderItem;
import com.cc.oms.entities.User;
import com.cc.oms.exception.OrderNotFoundException;
import com.cc.oms.model.Basket;
import com.cc.oms.service.ShoppingService;

@RestController
@RequestMapping("/purchase")
public class ShoppingController {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingController.class);
	private static final String ORDER_PLACED = "Order has been placed successfully";
	private static final String ITEM_ADDED = "Item added to basket.";
	private static final String ITEM_DELETED = "Item deleted from basket.";

	@Autowired
	ShoppingService shoppingService;

	@Autowired
	Basket basket;

	@Autowired
	User user;

	@PostMapping("/basket")
	public ResponseEntity<String> addItemsToBasket(@RequestBody OrderItem orderItem) {
			LOG.debug("***Starting addToBasket***");
			List<OrderItem> itemsInBasket = basket.getOrderItems();
			if (null == itemsInBasket) {
				itemsInBasket = new ArrayList<>();
			}
			if (!itemsInBasket.contains(orderItem)) {
				itemsInBasket.add(orderItem);
				LOG.debug("****Adding OrderItems**");
			}
			basket.setOrderItems(itemsInBasket);
			LOG.debug("***ending addToBasket***");

		return new ResponseEntity<String>(ITEM_ADDED, HttpStatus.OK);
	}

	@DeleteMapping("/basket")
	public ResponseEntity<String> deleteItemsFromBasket(@RequestBody OrderItem orderItem) {

		LOG.debug("***Starting deleteFromBasket***");
		List<OrderItem> itemsInBasket = basket.getOrderItems();
		if (null != itemsInBasket && !itemsInBasket.isEmpty() && itemsInBasket.contains(orderItem)) {
			itemsInBasket.remove(orderItem);
			LOG.debug("***removed from basket***");
			basket.setOrderItems(itemsInBasket);
		}

		LOG.debug("***ending deleteFromBasket***");

		return new ResponseEntity<String>(ITEM_DELETED, HttpStatus.OK);
	}

	@PostMapping("/neworder")
	public ResponseEntity<String> createOrder() {

		LOG.debug("***Starting Order***");
		List<OrderItem> itemsInBasket = basket.getOrderItems();
		LOG.debug("**Basket size is ***" + itemsInBasket.size());
		BigDecimal amountToPay = calculateTotalAmount(itemsInBasket);
		boolean isPaymentSuccessful = getPayments(amountToPay);

		if (isPaymentSuccessful) {
			shoppingService.createOrder(basket, user.getUserid());
			basket.setOrderItems(new ArrayList<OrderItem>());
		}

		LOG.debug("***ending Order***");

		return new ResponseEntity<String>(ORDER_PLACED, HttpStatus.CREATED);
	}

	private boolean getPayments(BigDecimal amountToPay) {
		LOG.debug("amountToPay***" + amountToPay);
		return true;
	}

	private BigDecimal calculateTotalAmount(List<OrderItem> itemsInBasket) {
		
		BigDecimal totalPrice = new BigDecimal("0.0");

		for (OrderItem orderItem : itemsInBasket)
		{
			Item item = orderItem.getItem();
			BigDecimal price = item.getPrice();
			int qty = orderItem.getQuantity();
			BigDecimal qtyBig = new BigDecimal(qty);
			BigDecimal itemsPrice = price.multiply(qtyBig);
			totalPrice = totalPrice.add(itemsPrice);
		}
		return totalPrice;
	}

	@GetMapping("/orders")
	public ResponseEntity<List<CustOrder>> getAllOrdersForUser()
	{
		int userId = user.getUserid();
		List<CustOrder> custOrders = shoppingService.getAllOrdersForUser(userId);
		return new ResponseEntity<List<CustOrder>>(custOrders, HttpStatus.OK);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<CustOrder> getOrderById(@PathVariable int orderId) {
		CustOrder custOrder = new CustOrder();

		custOrder = shoppingService.getOrderById(orderId);
		return new ResponseEntity<CustOrder>(custOrder, HttpStatus.OK);

	}

}
