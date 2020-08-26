package com.abcorp.promotion.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcorp.promotion.domain.Promotion;
import com.abcorp.promotion.domain.Sku;

@Component
public class PromotionUtil {
	
	@Autowired
	Map<String, Sku> skuProducts;
	
	public List<Promotion> getPromotions(){
		// TODO : read from config/bean later
		
		List<Promotion> promotions = new ArrayList<Promotion>();
		Promotion promotion1 = new Promotion("NIP","A",3,130);
		promotions.add(promotion1);
		Promotion promotion2 = new Promotion("NIP","B",2,45);
		promotions.add(promotion2);
		Promotion promotion3 = new Promotion("COMP","A|B",0,30);
		promotions.add(promotion3);
		Promotion promotion4 = new Promotion("PERP","D",0,10);
		promotions.add(promotion4);
		return promotions;
		
	}
	
	public Double applyPromotions(Map<String, Integer> cart) {
		
		List<Promotion> promotions = getPromotions();
		
		Set<String> cartProductCodes = cart.keySet();
		
		boolean isPromotionApplied = false;
		Promotion appliedPromotion = null;
		
		for(int i=0 ; i < promotions.size() ; i++) {
			Promotion promotion = promotions.get(i);
			String[] promPrdCds = promotion.getProductCodes().split("|");
			
			if(promPrdCds.length > 1) {
				// Check for all to be present in the cart, if yes then this promotion can be applied
				// mark isPromotionApplied = true;
				// appliedPromotion = promotion;
				
			}else {
				// Check for single prdCode to be present
				// mark isPromotionApplied = true;
				// appliedPromotion = promotion;
			}
			
			if(isPromotionApplied)
				break;
			
		}
		
//		return applyPromotionOnCartForFinalValue(cart, appliedPromotion);
		
		
		return 100.5; //TODO : replace with actual after calculation
		
	}

	private Double applyPromotionOnCartForFinalValue(Map<String, Integer> cart, Promotion appliedPromotion) {
		Double finalValue = null;
		if(appliedPromotion == null) {
			finalValue = calculateEachItemByPrice(cart);
		}
		else {
			// Calculate
		}
		return finalValue;
	}

	private Double calculateEachItemByPrice(Map<String, Integer> cart) {
		// TODO Auto-generated method stub
		return null;
	}

}
