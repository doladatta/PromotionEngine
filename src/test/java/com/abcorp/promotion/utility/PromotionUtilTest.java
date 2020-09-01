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
	void testApplyPromotionsForNoApplicablePromotions() {
		
		// Given
		testCart.getCart().put("A", 1);
		Double expectedCartValue = 50.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
	}
	
	@Test
	void testApplyPromotionsNIPPromotionsOnA() {
		
		// Given
		testCart.getCart().put("A", 3);
		Double expectedCartValue = 130.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
		assertEquals("NIP",actualInvoice.getAppliedPromotions().get(0).getPromotionCode());
	}
	
	@Test
	void testApplyPromotionsNIPPromotionsOnB() {
		
		// Given
		testCart.getCart().put("B", 2);
		Double expectedCartValue = 45.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
		assertEquals("NIP",actualInvoice.getAppliedPromotions().get(0).getPromotionCode());
	}
	
	@Test
	void testApplyPromotionsCOMPPromotionsOnCnD() {
		
		// Given
		testCart.getCart().put("C", 1);
		testCart.getCart().put("D", 1);
		Double expectedCartValue = 30.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
		assertEquals("COMP",actualInvoice.getAppliedPromotions().get(0).getPromotionCode());
	}
	
	@Test
	void testApplyPromotionsTestCase1A1B1C1() {
		
		// Given
		testCart.getCart().put("A", 1);
		testCart.getCart().put("B", 1);
		testCart.getCart().put("C", 1);
		Double expectedCartValue = 100.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
	}

	@Test
	void testApplyPromotionsTestCase2A5B5C1() {
		
		// Given
		testCart.getCart().put("A", 5);
		testCart.getCart().put("B", 5);
		testCart.getCart().put("C", 1);
		Double expectedCartValue = 370.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
	}
	
	@Test
	void testApplyPromotionsTestCase3A3B5C1D1() {
		
		// Given
		testCart.getCart().put("A", 3);
		testCart.getCart().put("B", 5);
		testCart.getCart().put("C", 1);
		testCart.getCart().put("D", 1);
		Double expectedCartValue = 280.00;
		// When
		Invoice actualInvoice = promotionUtil.applyPromotions(testCart);
		// Then
		assertEquals(expectedCartValue, actualInvoice.getValue());
	}
}
