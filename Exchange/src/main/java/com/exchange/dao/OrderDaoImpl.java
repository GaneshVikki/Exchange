package com.exchange.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.exchange.dao.repository.OrderRepository;
import com.exchange.dao.specification.OrderSpecification;
import com.exchange.domain.OrderRequest;
import com.exchange.entity.OrderEntity;

public class OrderDaoImpl implements OrderDao {
	
	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public List<OrderEntity> getOders(OrderRequest request) {
		List<OrderEntity> orders = null;
		orders = orderRepository.findAll(OrderSpecification.getOrder(request));
		return orders;
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

	@Override
	public OrderEntity insertOder(OrderEntity order) {
		OrderEntity orderEntity = orderRepository.save(order);
		return orderEntity;
	}

	@Override
	public List<OrderEntity> getOdersByOrderType(String orderType) {
		List<OrderEntity> orders = orderRepository.findByOrderType(orderType);
		return orders;
	}

}
