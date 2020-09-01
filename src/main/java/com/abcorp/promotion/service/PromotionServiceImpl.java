package com.abcorp.promotion.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.utility.PromotionUtil;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PromotionServiceImpl implements PromotionService{
	
	private static final Logger logger = LogManager.getLogger(PromotionServiceImpl.class);
	
	@NonNull
	private PromotionUtil promotionUtil;
	
	@Override
	public Invoice calculateTotalOrderValue(final Cart cart) {
		logger.info("In service Cart : {}", cart);
		return promotionUtil.applyPromotions(cart);
		
	}

}
