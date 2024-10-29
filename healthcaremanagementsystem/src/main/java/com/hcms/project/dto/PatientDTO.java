package com.hcms.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PatientDTO {
	
	@NotBlank(message = "Patient username must not be null")
	@Size(min = 8, max= 25, message = "Patient username must be at least 8 and 25 maximum.")
	private String patientUsername;
	
	@NotBlank(message = "Password colum must be filled!")
	@Size(min = 8, max= 25, message = "Password must be at least 8 and 25 maximum.")
	private String patientPassword;
	

	@NotBlank(message = "Patient name can't be empty")
	@Size(min = 5, max= 25, message = "Patient name must be at least 5 and 25 maximum.")
    private String patientName;
	
	@Size(min = 2, max= 10, message = "Field must be filled!")
    private String patientBloodGroup;
    
    @NotBlank(message = "Patient Gender can't be empty")
    @Size(min=4, max=10, message = "Gender must be 4 to 10 letters")
    private String patientGender;


    @NotNull
    private ContactDTO contact;

 
    @NotNull
    private AddressDTO address;
    
    


	public PatientDTO() {
		super();
	}


	public PatientDTO(
			@NotBlank(message = "Patient username must not be null") @Size(min = 8, max = 25, message = "Patient username must be at least 8 and 25 maximum.") String patientUsername,
			@NotBlank(message = "Password colum must be filled!") @Size(min = 8, max = 25, message = "Password must be at least 8 and 25 maximum.") String patientPassword,
			@NotBlank(message = "Patient name can't be empty") @Size(min = 5, max = 25, message = "Patient name must be at least 5 and 25 maximum.") String patientName,
			@Size(min = 2, max = 10, message = "Field must be filled!") String patientBloodGroup,
			@NotBlank(message = "Patient Gender can't be empty") @Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters") String patientGender,
			@NotNull ContactDTO contact, @NotNull AddressDTO address) {
		super();
		this.patientUsername = patientUsername;
		this.patientPassword = patientPassword;
		this.patientName = patientName;
		this.patientBloodGroup = patientBloodGroup;
		this.patientGender = patientGender;
		this.contact = contact;
		this.address = address;
	}


	public String getPatientUsername() {
		return patientUsername;
	}


	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}


	public String getPatientPassword() {
		return patientPassword;
	}


	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public String getPatientBloodGroup() {
		return patientBloodGroup;
	}


	public void setPatientBloodGroup(String patientBloodGroup) {
		this.patientBloodGroup = patientBloodGroup;
	}


	public String getPatientGender() {
		return patientGender;
	}


	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
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
		return "PatientDTO [patientUsername=" + patientUsername + ", patientPassword=" + patientPassword
				+ ", patientName=" + patientName + ", patientBloodGroup=" + patientBloodGroup + ", patientGender="
				+ patientGender + ", contact=" + contact + ", address=" + address + "]";
	}
    
    


}
