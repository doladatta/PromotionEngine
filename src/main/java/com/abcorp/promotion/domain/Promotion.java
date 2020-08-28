package com.abcorp.promotion.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Promotion {
	
	private String promotionCode;
	private String productCodes;
	private int quantity;
	private double promotionValue;
	

}
