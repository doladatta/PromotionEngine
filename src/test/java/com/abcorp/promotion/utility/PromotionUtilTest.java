package com.abcorp.promotion.utility;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;

class PromotionUtilTest {
	
	private Cart testCart;
	private PromotionUtil promotionUtil;

	@BeforeEach
	void setUp() throws Exception {
		testCart = new Cart();
		Map<String, Integer> cart = new HashMap<String, Integer>();
		testCart.setCart(cart );
		
		promotionUtil = new PromotionUtil();
	}

	@Test
	void testApplyPromotions() {
		
		// Given
		testCart.getCart().put("A", 1);
		Double expectedCartValue = 50.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
	}

}
