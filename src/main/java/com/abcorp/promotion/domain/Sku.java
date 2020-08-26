package com.abcorp.promotion.domain;

public class Sku {
	private String procuctCode;
	private Double unitPrice;
	public Sku() {}
	public Sku(String procuctCode, Double unitPrice) {
		this.procuctCode = procuctCode;
		this.unitPrice = unitPrice;
	}
	public String getProcuctCode() {
		return procuctCode;
	}
	public void setProcuctCode(String procuctCode) {
		this.procuctCode = procuctCode;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Override
	public String toString() {
		return "Sku [procuctCode=" + procuctCode + ", unitPrice=" + unitPrice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((procuctCode == null) ? 0 : procuctCode.hashCode());
		result = prime * result + ((unitPrice == null) ? 0 : unitPrice.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sku other = (Sku) obj;
		if (procuctCode == null) {
			if (other.procuctCode != null)
				return false;
		} else if (!procuctCode.equals(other.procuctCode))
			return false;
		if (unitPrice == null) {
			if (other.unitPrice != null)
				return false;
		} else if (!unitPrice.equals(other.unitPrice))
			return false;
		return true;
	}
	
	

}
