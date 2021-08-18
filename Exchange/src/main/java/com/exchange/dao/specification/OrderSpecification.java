package com.exchange.dao.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.exchange.domain.OrderRequest;
import com.exchange.entity.OrderEntity;

public class OrderSpecification{

	public static Specification<OrderEntity> getOrder(OrderRequest request) {
		return (root, query, cb) -> {
			 Predicate pc = null;
			 if(request.getSymbol()!=null) {
				 cb.equal(root.get("STOCK"), request.getSymbol());
			 }
			 if(request.getPrice()!=null) {
				 cb.equal(root.get("PRICE"), request.getPrice());
			 }
	         return pc;
	     };
	}

}
