package com.abcorp.promotion.domain;

public class Promotion {
	
	private String promotionCode;
	private String productCodes;
	private int quantity;
	private double promotionValue;
	public Promotion(String promotionCode, String productCodes, int quantity, double promotionValue) {
		this.promotionCode=promotionCode;
		this.productCodes=productCodes;
		this.quantity=quantity;
		this.promotionValue=promotionValue;
	}
	public String getPromotionCode() {
		return promotionCode;
	}
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}
	public String getProductCodes() {
		return productCodes;
	}
	public void setProductCodes(String productCodes) {
		this.productCodes = productCodes;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPromotionValue() {
		return promotionValue;
	}
	public void setPromotionValue(double promotionValue) {
		this.promotionValue = promotionValue;
	}
	@Override
	public String toString() {
		return "Promotion [promotionCode=" + promotionCode + ", productCodes=" + productCodes + ", quantity=" + quantity
				+ ", promotionValue=" + promotionValue + "]";
	}
	
	

}
