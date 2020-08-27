package com.abcorp.promotion.domain;

import java.util.List;

public class Invoice {
	private List<Promotion> appliedPromotions;
	private Double value;
	private Cart cart;
	public List<Promotion> getAppliedPromotions() {
		return appliedPromotions;
	}
	public void setAppliedPromotions(List<Promotion> appliedPromotions) {
		this.appliedPromotions = appliedPromotions;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
