package com.abcorp.promotion.domain;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Cart {
	
	@JsonProperty
	private Map<String, Integer> cart;

}
