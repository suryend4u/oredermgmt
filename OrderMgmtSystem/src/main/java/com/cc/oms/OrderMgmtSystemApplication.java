package com.cc.oms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.cc.oms.entities.User;
import com.cc.oms.model.Basket;
import com.cc.oms.service.ShoppingService;
import com.cc.oms.service.ShoppingServiceImpl;

@SpringBootApplication
public class OrderMgmtSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMgmtSystemApplication.class, args);
	}

	@Bean
	ShoppingService getShoppingService() {
		return new ShoppingServiceImpl();
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Basket sessionScopedBasketBean() {
		return new Basket();
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public User sessionScopedUserBean() {

		User user = new User();
		user.setName("JOHN DOE");
		user.setPhone("8981249686");
		user.setUserid(1);
		return user;
	}

}

