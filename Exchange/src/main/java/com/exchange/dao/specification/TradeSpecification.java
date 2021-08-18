package com.exchange.dao.specification;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.exchange.domain.TradeRequest;
import com.exchange.entity.TradeEntity;

public class TradeSpecification {
	
	public static Specification<TradeEntity> getOrder(TradeRequest request) {
		return (root, query, cb) -> {
			 Predicate pc = null;
			 if(request.getParties()!=null) {
				 pc = cb.equal(root.get("seller"), request.getParties());
				 pc = cb.or(pc, cb.equal(root.get("buyer"), request.getParties()));
			 }
			 if(request.getStock()!=null) {
				 if(pc!=null)
					 pc = cb.and(pc, cb.equal(root.get("stock"), request.getStock()));
				 else
					 pc = cb.equal(root.get("stock"), request.getStock());
			 }
			 if(request.getTradeDate()!=null) {
				 if(pc!=null)
					 pc = cb.and(pc, cb.equal(root.get("tradeDate"), request.getTradeDate()));
				 else
					 pc = cb.equal(root.get("tradeDate"), request.getTradeDate());
			 }
	         return pc;
	     };
	}
}
