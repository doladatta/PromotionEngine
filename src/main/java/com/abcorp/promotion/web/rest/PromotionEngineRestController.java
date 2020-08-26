package com.abcorp.promotion.web.rest;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PromotionEngineRestController {
	
	@PostMapping(value = "/cart/promotions/apply", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> applyPromotionsToCart(@RequestBody Map<String, Integer> cart) throws Exception {
		
		
		return ResponseEntity.ok("OK");
		
	}

}
