package com.hcms.project.dto;

import jakarta.validation.constraints.NotBlank;

public class AddressDTO {
	
	@NotBlank(message = "Field cannot be left empty.")
    private String line;
    @NotBlank(message = "Field cannot be left empty.")
    private String houseNumber;
    @NotBlank(message = "Field cannot be left empty.")
    private String street;
    @NotBlank(message = "Field cannot be left empty.")
    private String pincode;
    @NotBlank(message = "Field cannot be left empty.")
    private String city;
    
    
    
	public AddressDTO() {
		super();
	}
	public AddressDTO(@NotBlank(message = "Field cannot be left empty.") String line,
			@NotBlank(message = "Field cannot be left empty.") String houseNumber,
			@NotBlank(message = "Field cannot be left empty.") String street,
			@NotBlank(message = "Field cannot be left empty.") String pincode,
			@NotBlank(message = "Field cannot be left empty.") String city) {
		super();
		this.line = line;
		this.houseNumber = houseNumber;
		this.street = street;
		this.pincode = pincode;
		this.city = city;
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
		return "AddressDTO [line=" + line + ", houseNumber=" + houseNumber + ", street=" + street + ", pincode="
				+ pincode + ", city=" + city + "]";
	}
    
    
    

}
