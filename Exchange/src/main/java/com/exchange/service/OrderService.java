package com.exchange.service;

import java.util.List;

import com.exchange.domain.Order;
import com.exchange.domain.OrderRequest;

public interface OrderService {

	List<Order> getOrders(OrderRequest request);
	
	String submitOrder(Order order);

}
