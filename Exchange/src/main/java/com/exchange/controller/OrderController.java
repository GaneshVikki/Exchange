package com.exchange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.domain.Order;
import com.exchange.domain.OrderForm;
import com.exchange.domain.OrderRequest;
import com.exchange.service.OrderService;

@RestController
@RequestMapping(path = "exchange/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping(path = "/get" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrderList(
			@RequestParam(name = "symbol") String symbol, 
			@RequestParam(name = "price") String price){
		HttpHeaders headers = new HttpHeaders();
		OrderRequest request = new OrderRequest();
		request.setSymbol(symbol);
		request.setPrice(price);
		List<Order> orders = orderService.getOrders(request);
		if(orders != null && !orders.isEmpty()) {
			return new ResponseEntity<List<Order>>(orders, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Order>>(null, headers, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/submit" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> submitOrder(
			@RequestBody OrderForm form)  {
		HttpHeaders headers = new HttpHeaders();
		Order order = new Order();
		order.setParty(form.getParty());
		order.setOrderType(form.getOrderType());
		order.setStock(form.getStock());
		order.setPrice(form.getPrice());
		String status = orderService.submitOrder(order);
		if(status != null && !status.isEmpty()) {
			return new ResponseEntity<String>(null, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(null, headers, HttpStatus.NOT_FOUND);
		}
	}
}
