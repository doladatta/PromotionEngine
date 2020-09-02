package com.abcorp.promotion.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;

public class PromotionServiceImplTest {

	private PromotionServiceImpl promotionServiceImpl;

	private Cart testCart;

	@BeforeEach
	public void setUp() throws Exception {
		testCart = new Cart();
		Map<String, Integer> cart = new HashMap<String, Integer>();
		testCart.setCart(cart);
		promotionServiceImpl = new PromotionServiceImpl();
	}

	@Test
	public void testCalculateTotalOrderValue() {

		// Given
		Map<String, Integer> cartItemMap = testCart.getCart();
		cartItemMap.put("A", 1);
		Double expectedValue = 50.00;
		// When
		Invoice actualInvoice = promotionServiceImpl.calculateTotalOrderValue(testCart);
		// Then
		assertEquals(actualInvoice.getCart(), testCart);
		assertEquals(actualInvoice.getValue(), expectedValue);

	}

}
