package com.money.api.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String fullAddress;
	private String city;
	private String province;
	private String zipCode;
	
	
	public String getFullAddress() {
		return fullAddress;
	}
	
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
