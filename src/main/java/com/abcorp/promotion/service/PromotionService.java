package com.abcorp.promotion.service;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;

public interface PromotionService {
	
	Invoice calculateTotalOrderValue(Cart cart) ;

}
