package com.cc.oms;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.cc.oms.entities.CustOrder;
import com.cc.oms.repo.OrderRepository;
import com.cc.oms.service.ShoppingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingServiceTest {

	@InjectMocks
	ShoppingServiceImpl shoppingService;

	@Mock
	OrderRepository orderRepository;

	@Test
	public void getAllOrdersForUserTest() {

		List<CustOrder> custOrderList = new ArrayList<>();
		CustOrder custOrder1 = new CustOrder();
		custOrder1.setOrderDate(new Date());
		custOrder1.setOrderId(1);
		custOrder1.setUserId(1);
		custOrder1.setOrderstatus("SUCCESS");

		CustOrder custOrder2 = new CustOrder();
		custOrder2.setOrderDate(new Date());
		custOrder2.setOrderId(2);
		custOrder2.setUserId(1);
		custOrder2.setOrderstatus("FAILURE");

		CustOrder custOrder3 = new CustOrder();
		custOrder3.setOrderDate(new Date());
		custOrder3.setOrderId(3);
		custOrder3.setUserId(1);
		custOrder3.setOrderstatus("SUCCESS");

		custOrderList.add(custOrder1);
		custOrderList.add(custOrder2);
		custOrderList.add(custOrder3);

		when(orderRepository.findByUserId(1)).thenReturn(custOrderList);

		// TEST
		List<CustOrder> custOrderListFromShoppingService = shoppingService.getAllOrdersForUser(1);
		assertEquals(3, custOrderListFromShoppingService.size());
	}

	@Test
	public void getOrderByIdTest() {

		Optional<CustOrder> custOrder = Optional.of(new CustOrder());
		custOrder.get().setOrderDate(new Date());
		custOrder.get().setOrderId(1);
		custOrder.get().setUserId(1);
		custOrder.get().setOrderstatus("SUCCESS");
		// custOrder.get().se

		when(orderRepository.findById(1)).thenReturn(custOrder);

		CustOrder custOrderFromSvc = shoppingService.getOrderById(1);
		assertEquals(1, custOrderFromSvc.getOrderId());
		assertEquals(1, custOrderFromSvc.getUserId());
		assertEquals("SUCCESS", custOrderFromSvc.getOrderstatus());
	}

}
