package com.hcms.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    
//    @NotBlank(message = "Field cannot be left empty.")
    private String line;
//    @NotBlank(message = "Field cannot be left empty.")
    private String houseNumber;
//    @NotBlank(message = "Field cannot be left empty.")
    private String street;
//    @NotBlank(message = "Field cannot be left empty.")
    private String pincode;
//    @NotBlank(message = "Field cannot be left empty.")
    private String city;
    
    public Address() {
		super();
	}
    
	
    
	public Address(Long addressId, String line, String houseNumber, String street, String pincode, String city) {
		super();
		this.addressId = addressId;
		this.line = line;
		this.houseNumber = houseNumber;
		this.street = street;
		this.pincode = pincode;
		this.city = city;
	}



	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", line=" + line + ", houseNumber=" + houseNumber + ", street="
				+ street + ", pincode=" + pincode + ", city=" + city + "]";
	}
    
    
    
}
