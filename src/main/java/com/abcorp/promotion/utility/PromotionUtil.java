package com.abcorp.promotion.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.abcorp.promotion.domain.Cart;
import com.abcorp.promotion.domain.Invoice;
import com.abcorp.promotion.domain.Promotion;
import com.abcorp.promotion.domain.Sku;

@Component
public class PromotionUtil {
	
	private static final Logger logger = LogManager.getLogger(PromotionUtil.class);
	
	private final Map<String, Sku> skuProducts = configureSkus();
	private final int noOfPromotionsApplicable = 3;
	private final List<Promotion> promotions = configurePromotions();
	
	
	public Map<String, Sku> getSkuProducts() {
		return skuProducts;
	}

	public int getNoOfPromotionsApplicable() {
		return noOfPromotionsApplicable;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	private List<Promotion> configurePromotions(){
		// TODO : read from config/bean later
		List<Promotion> promotions = new ArrayList<Promotion>();
		Promotion promotion1 = new Promotion("NIP","A",3,130);
		promotions.add(promotion1);
		Promotion promotion2 = new Promotion("NIP","B",2,45);
		promotions.add(promotion2);
		Promotion promotion3 = new Promotion("COMP","C,D",1,30);
		promotions.add(promotion3);
		Promotion promotion4 = new Promotion("PERP","E",0,10);
		promotions.add(promotion4);
		return promotions;
	}
	
	private Map<String, Sku> configureSkus(){
		Map<String, Sku> skuProducts = new HashMap<String, Sku>();
		skuProducts.put("A", new Sku("A", 50.0));
		skuProducts.put("B", new Sku("B", 30.0));
		skuProducts.put("C", new Sku("C", 20.0));
		skuProducts.put("D", new Sku("A", 15.0));
		return skuProducts;
	}
	
	public Invoice applyPromotions(final Cart cartObj) {
		
		// Copy Cart
		Map<String, Integer> cartDuplicate = new HashMap<String, Integer>();
		cartDuplicate.putAll(cartObj.getCart());
		
		Invoice invoice = new Invoice();
		invoice.setCart(cartObj);
		
		Map<String, Integer> cart = cartDuplicate;
		
		Set<String> cartProductCodes = cart.keySet();
		
		boolean isPromotionApplied = false;
		
		List<Promotion> aplicablePromotions = new ArrayList<Promotion>();
		
		for(int i=0 ; i < promotions.size() ; i++) {
			Promotion promotion = promotions.get(i);
			String[] promPrdCds = promotion.getProductCodes().split(",");
			
			if(promPrdCds.length > 1) {
				// Check for all to be present in the cart, if yes then this promotion can be applied
				boolean promoApplicable = true;
				for(int j=0; j < promPrdCds.length; j++) {
					
					if(cartProductCodes.contains(promPrdCds[j]) && cart.get(promPrdCds[j]) >= promotion.getQuantity()) {
						promoApplicable = promoApplicable && true;
					}
					else {
						promoApplicable = promoApplicable && false;
					}
					
				}
				if(promoApplicable) {
					aplicablePromotions.add(promotion);
					if(aplicablePromotions.size()==noOfPromotionsApplicable) {
						isPromotionApplied = true;
					}
				}
				
			}else {
				// Check for single prdCode to be present
				if(cartProductCodes.contains(promPrdCds[0]) && cart.get(promPrdCds[0]) >= promotion.getQuantity()) {
					aplicablePromotions.add(promotion);
					if(aplicablePromotions.size()==noOfPromotionsApplicable) {
						isPromotionApplied = true;
					}
				}
			}
			
			if(isPromotionApplied)
				break;
			
		}
		
		Double value = applyPromotionOnCartForFinalValue(cart, aplicablePromotions);
		invoice.setAppliedPromotions(aplicablePromotions);
		invoice.setValue(value);
		
		return invoice ;
		
		
	}

	private Double applyPromotionOnCartForFinalValue(Map<String, Integer> cart, List<Promotion> aplicablePromotions) {
		Double finalValue = 0.0;
		if(CollectionUtils.isEmpty(aplicablePromotions)) {
			finalValue = calculateEachItemByPrice(cart);
		}
		else {
			for(Promotion promotion : aplicablePromotions) {
				finalValue = finalValue + applyPromotionOnCartAndUpdateCartQty(cart, promotion);
			}
			finalValue = finalValue + calculateEachItemByPrice(cart);
		}
		return finalValue;
	}

	private Double applyPromotionOnCartAndUpdateCartQty(Map<String, Integer> cart, Promotion appliedPromotion) {
		Double finalValue = 0.0;
		if(appliedPromotion == null) {
			finalValue = calculateEachItemByPrice(cart);
		}
		else {
			// Calculate
			String[] promPrdCds = appliedPromotion.getProductCodes().split(",");
			int promoQty = appliedPromotion.getQuantity();
			Double promoval = appliedPromotion.getPromotionValue();
			String promoCode = appliedPromotion.getPromotionCode();
			if(promoCode.equals("NIP")) {
				logger.info("Applying promotion NIP");
				int orderedQty = cart.get(promPrdCds[0]);
				while(orderedQty>=promoQty) {
					finalValue = finalValue + promoval;
					orderedQty = orderedQty - promoQty;
				}
				Sku sku = skuProducts.get(promPrdCds[0]);
				finalValue = finalValue + 
						(orderedQty*sku.getUnitPrice());
				cart.put(promPrdCds[0],0);
				
			}else if(promoCode.equals("PERP")) {
				// Percentage calculation TBD
			}else if(promoCode.equals("COMP")) {
				logger.info("Applying promotion COMP");
				int noOfPrmPrd = promPrdCds.length;
				int minQty = 0;
				for(int i=0; i<noOfPrmPrd ; i++) {
					if(i==0)
						minQty = cart.get(promPrdCds[i]);
					else
						minQty = Math.min(minQty, cart.get(promPrdCds[i]));
				}
				
				finalValue = finalValue + minQty*promoval;
				
				for(int i=0; i<noOfPrmPrd ; i++) {
					int reducedQty = cart.get(promPrdCds[i]) - minQty;
					cart.put(promPrdCds[i], reducedQty);
				}
				
			}
			
			
			
		}
		return finalValue;
	}

	private Double calculateEachItemByPrice(Map<String, Integer> cart) {
		Double finalCartValue = 0.0;
		
		Set<String> cartPrds = cart.keySet();
		for(String prdCd : cartPrds) {
			Sku sku = skuProducts.get(prdCd);
			finalCartValue = finalCartValue + 
					(cart.get(prdCd)*sku.getUnitPrice());
		}
		return finalCartValue;
	}

}
