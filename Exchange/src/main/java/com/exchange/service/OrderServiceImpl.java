package com.exchange.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.exchange.dao.OrderDao;
import com.exchange.dao.TradeDao;
import com.exchange.domain.Order;
import com.exchange.domain.OrderRequest;
import com.exchange.entity.OrderEntity;
import com.exchange.entity.TradeEntity;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	TradeDao tradeDao;
	
	@Override
	public List<Order> getOrders(OrderRequest request) {
		
		List<OrderEntity> orderEntities = orderDao.getOders(request);
		List<Order> orders = new ArrayList<>();
		for(OrderEntity entity : orderEntities) {
			Order order = new Order();
			order.setParty(entity.getParty());
			order.setOrderType(entity.getOrderType());
			order.setStock(entity.getStock());
			order.setPrice(entity.getPrice());
			orders.add(order);
		}
		return orders;
	}

	@Override
	public String submitOrder(Order order) {
		boolean exist = false;
		if(order.getOrderType().equals("SELL")) {
			List<OrderEntity> oldBuyOrders = orderDao.getOdersByOrderType("BUY");
			
			for(OrderEntity orderLocal : oldBuyOrders) {
				if(orderLocal.getStock().equals(order.getStock())
						&& orderLocal.getPrice().equals(order.getPrice())) {
					TradeEntity trade = new TradeEntity();
					trade.setBuyer(orderLocal.getParty());
					trade.setSeller(order.getParty());
					trade.setStock(order.getStock());
					trade.setPrice(order.getPrice());
					trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
					orderDao.deleteOrder(orderLocal.getId());
					tradeDao.insertTrade(trade);
					exist = true;
				}
			}
			
			if(!exist) {
				insertNonMatchingOrder(order);
			}
		} else if(order.getOrderType().equals("BUY")) {
			List<OrderEntity> oldBuyOrders = orderDao.getOdersByOrderType("SELL");
			for(OrderEntity orderLocal : oldBuyOrders) {
				if(orderLocal.getStock().equals(order.getStock())
						&& orderLocal.getPrice().equals(order.getPrice())) {
					TradeEntity trade = new TradeEntity();
					trade.setBuyer(order.getParty());
					trade.setSeller(orderLocal.getParty());
					trade.setStock(order.getStock());
					trade.setPrice(order.getPrice());
					trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
					orderDao.deleteOrder(orderLocal.getId());
					tradeDao.insertTrade(trade);
					exist = true;
				}
			}
			if(!exist) {
				insertNonMatchingOrder(order);
			}
		}
		return null;
	}

	/**
	 * @param order
	 */
	private void insertNonMatchingOrder(Order order) {
		OrderEntity orderEnitity = new OrderEntity();
		orderEnitity.setOrderType(order.getOrderType());
		orderEnitity.setParty(order.getParty());
		orderEnitity.setPrice(order.getPrice());
		orderEnitity.setStock(order.getStock());
		orderEnitity.setOrderType(order.getOrderType());
		orderDao.insertOder(orderEnitity);
	}

}
