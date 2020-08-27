package com.abcorp.promotion.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cart {
	
	@JsonProperty
	private Map<String, Integer> cart;

	public Map<String, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<String, Integer> cart) {
		this.cart = cart;
	}

	public Cart(Map<String, Integer> cart) {
		super();
		this.cart = cart;
	}
	public Cart() {}
	

}
