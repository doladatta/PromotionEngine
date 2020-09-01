package com.abcorp.promotion.web.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.service.PromotionService;
import com.abcorp.promotion.service.PromotionServiceImpl;

@RestController
public class PromotionEngineRestController {
	
	private static final Logger logger = LogManager.getLogger(PromotionEngineRestController.class);
	
	private PromotionService promotionService;
	
	public PromotionEngineRestController() {
		promotionService = new PromotionServiceImpl();
	}
	
	@PostMapping(value = "/cart/promotions/apply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> applyPromotionsToCart(@RequestBody final Cart cart) throws Exception {
		logger.debug("Received Cart : {}", cart);
		Invoice invoice = promotionService.calculateTotalOrderValue(cart);
		logger.debug("Generated Invoice : {}", invoice);
		return ResponseEntity.ok(invoice);
		
	}

}
