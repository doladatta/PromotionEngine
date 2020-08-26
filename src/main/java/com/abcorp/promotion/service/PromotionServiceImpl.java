package com.abcorp.promotion.service;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService{

	@Override
	public Double calculateTotalOrderValue(Map<String, Double> cart) {
		// TODO Auto-generated method stub
		return 10.5;
	}

}
