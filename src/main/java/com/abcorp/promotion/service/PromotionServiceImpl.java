package com.abcorp.promotion.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcorp.promotion.utility.PromotionUtil;

@Service
public class PromotionServiceImpl implements PromotionService{
	
//	@Autowired
//	Map<String, Sku> skuProducts;

	@Autowired
	private PromotionUtil promotionUtil;
	
	@Override
	public Double calculateTotalOrderValue(Map<String, Integer> cart) {
		// TODO Auto-generated method stub
//		return promotionUtil.applyPromotions(cart);
		
		return 10.5;
	}

}
