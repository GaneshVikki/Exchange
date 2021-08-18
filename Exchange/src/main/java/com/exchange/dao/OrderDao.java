package com.exchange.dao;

import java.util.List;

import com.exchange.domain.OrderRequest;
import com.exchange.entity.OrderEntity;

public interface OrderDao {
	
	public List<OrderEntity> getOders(OrderRequest request);
	
	public List<OrderEntity> getOdersByOrderType(String orderType);
	
	public void deleteOrder(Long id);
	
	public OrderEntity insertOder(OrderEntity order);
}
