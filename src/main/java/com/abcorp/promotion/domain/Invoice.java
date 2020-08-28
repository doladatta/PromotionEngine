package com.abcorp.promotion.domain;

import java.util.List;

import lombok.Data;

@Data
public class Invoice {
	private List<Promotion> appliedPromotions;
	private Double value;
	private Cart cart;

}
