package com.hcms.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateDoctorDTO {
	
		@NotBlank(message = "Doctor username must not be null")
		@Size(min = 8, max= 25, message = "Doctor username must be at least 8 and 25 maximum.")
		private String doctorUsername;
		
		@NotBlank(message = "Password colum must be filled!")
		@Size(min = 8, max= 25, message = "Password must be at least 8 and 25 maximum.")
		private String doctorPassword;
		

	    @NotBlank(message = "Doctor name should not be null.")
	    @Size(min=2, max = 255, message = "Doctor name should not be empty.")
	    private String doctorName;
	    
//	    @NotBlank(message = "Doctor email should not be null.")
//	    @Email(message = "Doctor email should be valid.")
//	    private String doctorEmail;
	    
	    @NotBlank(message = "Qualification must not be empty")
	    @Size(min =2,  message= "Qualifiction must be at least 2 charaters.")
	    private String doctorQualification;
	    
	    @NotBlank(message = "Qualification must not be empty")
	    @Size(min =2,  message= "Qualifiction must be at least 2 charaters.")
	    private String specialist;
	    
	    @NotBlank(message = "Doctor Gender can't be empty")
	    @Size(min=4, max=10, message = "Gender must be 4 to 10 letters")
	    private String doctorGender;

//	    @OneToOne
//	    @JoinColumn(name = "contact_id")
	    @NotNull
	    private ContactDTO contact;

//	    @OneToOne
//	    @JoinColumn(name = "address_id")
	    @NotNull
	    private AddressDTO address;
	    
	    

		public CreateDoctorDTO() {
	super();
}



		public CreateDoctorDTO(
				@NotBlank(message = "Doctor username must not be null") @Size(min = 8, max = 25, message = "Doctor username must be at least 8 and 25 maximum.") String doctorUsername,
				@NotBlank(message = "Password colum must be filled!") @Size(min = 8, max = 25, message = "Password must be at least 8 and 25 maximum.") String doctorPassword,
				@NotBlank(message = "Doctor name should not be null.") @Size(min = 2, max = 255, message = "Doctor name should not be empty.") String doctorName,
				@NotBlank(message = "Qualification must not be empty") @Size(min = 2, message = "Qualifiction must be at least 2 charaters.") String doctorQualification,
				@NotBlank(message = "Qualification must not be empty") @Size(min = 2, message = "Qualifiction must be at least 2 charaters.") String specialist,
				@NotBlank(message = "Doctor Gender can't be empty") @Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters") String doctorGender,
				@NotNull ContactDTO contact, @NotNull AddressDTO address) {
			super();
			this.doctorUsername = doctorUsername;
			this.doctorPassword = doctorPassword;
			this.doctorName = doctorName;
			this.doctorQualification = doctorQualification;
			this.specialist = specialist;
			this.doctorGender = doctorGender;
			this.contact = contact;
			this.address = address;
		}



		public String getDoctorUsername() {
			return doctorUsername;
		}



		public void setDoctorUsername(String doctorUsername) {
			this.doctorUsername = doctorUsername;
		}



		public String getDoctorPassword() {
			return doctorPassword;
		}



		public void setDoctorPassword(String doctorPassword) {
			this.doctorPassword = doctorPassword;
		}



		public String getDoctorName() {
			return doctorName;
		}



		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}



		public String getDoctorQualification() {
			return doctorQualification;
		}



		public void setDoctorQualification(String doctorQualification) {
			this.doctorQualification = doctorQualification;
		}



		public String getSpecialist() {
			return specialist;
		}



		public void setSpecialist(String specialist) {
			this.specialist = specialist;
		}



		public String getDoctorGender() {
			return doctorGender;
		}



		public void setDoctorGender(String doctorGender) {
			this.doctorGender = doctorGender;
		}



		public ContactDTO getContact() {
			return contact;
		}



		public void setContact(ContactDTO contact) {
			this.contact = contact;
		}



		public AddressDTO getAddress() {
			return address;
		}



		public void setAddress(AddressDTO address) {
			this.address = address;
		}



		@Override
		public String toString() {
			return "CreateDoctorDTO [doctorUsername=" + doctorUsername + ", doctorPassword=" + doctorPassword
					+ ", doctorName=" + doctorName + ", doctorQualification=" + doctorQualification + ", specialist="
					+ specialist + ", doctorGender=" + doctorGender + ", contact=" + contact + ", address=" + address
					+ "]";
		}



	    

	    
	    

}
