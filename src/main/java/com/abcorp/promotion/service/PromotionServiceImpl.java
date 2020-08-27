package com.abcorp.promotion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.utility.PromotionUtil;

@Service
public class PromotionServiceImpl implements PromotionService{
	
	@Autowired
	private PromotionUtil promotionUtil;
	
	@Override
	public Invoice calculateTotalOrderValue(final Cart cart) {
		return promotionUtil.applyPromotions(cart);
		
	}

}
