package com.hcms.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactDTO {
	
	 	@NotBlank(message = "Mobile number can not be blank.")
	    private String mobile;
	    @Email(message = "Email must be in proper format.")
	    @NotBlank(message = "Email can not be blank.")
	    private String email;
	    
	    
	    
		public ContactDTO() {
			super();
		}
		public ContactDTO(@NotBlank(message = "Mobile number can not be blank.") String mobile,
				@Email(message = "Email must be in proper format.") @NotBlank(message = "Email can not be blank.") String email) {
			super();
			this.mobile = mobile;
			this.email = email;
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
			return "ContactDTO [mobile=" + mobile + ", email=" + email + "]";
		}
	    
	    

}
