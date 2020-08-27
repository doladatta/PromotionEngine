package com.abcorp.promotion.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.service.PromotionService;

@RestController
public class PromotionEngineRestController {
	
	@Autowired
	private PromotionService promotionService;
	
	@PostMapping(value = "/cart/promotions/apply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> applyPromotionsToCart(@RequestBody final Cart cart) throws Exception {
		
		Invoice invoice = promotionService.calculateTotalOrderValue(cart);
		
		return ResponseEntity.ok(invoice);
		
	}

}
