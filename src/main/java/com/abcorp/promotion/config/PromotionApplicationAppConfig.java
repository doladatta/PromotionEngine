package com.abcorp.promotion.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abcorp.promotion.domain.Sku;

@Configuration
public class PromotionApplicationAppConfig {
	
	@Value("#{'${abc.item.price}'.split(',')}") 
	private List<String> itemPriceList;
	
	@Bean
	public Map<String, Sku> skuProducts(){
		
		Map<String, Sku> skuMap = new HashMap<String, Sku>();
		
		List<Sku> skus = itemPriceList.stream().map(itemPrice -> {
			String itemCode = itemPrice.substring(0, itemPrice.indexOf(':'));
			Double unitPrice = Double.valueOf(itemPrice.substring(itemPrice.indexOf(':')+1));
			return new Sku(itemCode, unitPrice);
		}).collect(Collectors.toList());
		
		skuMap = skus.stream().collect(Collectors.toMap(sku->sku.getProcuctCode(),sku->sku));
		
		return skuMap;
		
	}

}
