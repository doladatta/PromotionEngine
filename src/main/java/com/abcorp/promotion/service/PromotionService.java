package com.abcorp.promotion.service;

import java.util.Map;

public interface PromotionService {
	
	Double calculateTotalOrderValue(Map<String,Double> cart) ;

}
