package com.abcorp.promotion.service;

import org.springframework.stereotype.Service;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.utility.PromotionUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PromotionServiceImpl implements PromotionService{
	
	@NonNull
	private PromotionUtil promotionUtil;
	
	@Override
	public Invoice calculateTotalOrderValue(final Cart cart) {
		return promotionUtil.applyPromotions(cart);
		
	}

}
