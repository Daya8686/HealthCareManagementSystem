package com.hcms.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

//    @NotBlank(message = "Mobile number can not be blank.")
    @Column(nullable = false)
    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")
    private String mobile;
//    @Email(message = "Email must be in proper format.")
//    @NotBlank(message = "Email can not be blank.")
    @Column(nullable = false, unique = true)
    private String email;
    
    
    
	public Contact() {
		super();
	}
	
	public Contact(Long contactId, String mobile, String email) {
		super();
		this.contactId = contactId;
		this.mobile = mobile;
		this.email = email;
	}

	public Long getContactId() {
		return contactId;
	}
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Contact [contactId=" + contactId + ", mobile=" + mobile + ", email=" + email + "]";
	}
    
	
    
    
}