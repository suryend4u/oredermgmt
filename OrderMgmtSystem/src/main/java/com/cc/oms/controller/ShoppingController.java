package com.cc.oms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cc.oms.model.Basket;
import com.cc.oms.service.ShoppingService;

@RestController
@RequestMapping("/purchase")
public class ShoppingController {

	private static final Logger LOG = LoggerFactory.getLogger(ShoppingController.class);

	@Autowired
	ShoppingService shoppingService;

	@Autowired
	Basket basket;

	@Autowired
	User user;

	@PostMapping("/addToBasket")
	public void addItemsToBasket(@RequestBody OrderItem orderItem) {
		try {
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
		} catch (Exception e) {
			LOG.error("Error in adding to basket");
		}
	}

	@PostMapping("/deleteFromBasket")
	public void deleteItemsFromBasket(@RequestBody OrderItem orderItem) {

		try {
			LOG.debug("***Starting deleteFromBasket***");
			List<OrderItem> itemsInBasket = basket.getOrderItems();
			if (null != itemsInBasket && !itemsInBasket.isEmpty() && itemsInBasket.contains(orderItem)) {
				itemsInBasket.remove(orderItem);
				LOG.debug("***removed from basket***");
				basket.setOrderItems(itemsInBasket);
			}

			LOG.debug("***ending deleteFromBasket***");
		} catch (Exception e) {

			LOG.error("Error in deleting from Basket.");

		}
	}

	@GetMapping("/createOrder")
	public void createOrder() {

		try {
			LOG.debug("***Starting createOrder***");
			List<OrderItem> itemsInBasket = basket.getOrderItems();
			LOG.debug("**Basket size is ***" + itemsInBasket.size());
			BigDecimal amountToPay = calculateTotalAmount(itemsInBasket);
			boolean isPaymentSuccessful = getPayments(amountToPay);

			if (isPaymentSuccessful) {
				shoppingService.createOrder(basket, user.getUserid());
				basket.setOrderItems(new ArrayList<OrderItem>());
			}

			LOG.debug("***ending createOrder***");
		} catch (Exception e) {
			LOG.error("Error in creating Order from Basket.");
		}
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

	@GetMapping("/listAllOrders")
	public List<CustOrder> getAllOrdersForUser()
	{
		try {
			LOG.debug("***Starting listAllOrders***");

		} catch (Exception e) {
			LOG.error("Error in listing All Orders for user");
		}
		return shoppingService.getAllOrdersForUser(user.getUserid());
	}

	@GetMapping("/getOrder/{orderId}")
	public CustOrder getOrderById(@PathVariable int orderId) {
		CustOrder custOrder = new CustOrder();
		try {
			custOrder = shoppingService.getOrderById(orderId);
		} catch (Exception e) {
			LOG.error("Error in getting orderId " + orderId);
		}
		return custOrder;
	}

}
