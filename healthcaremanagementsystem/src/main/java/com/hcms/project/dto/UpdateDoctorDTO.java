package com.hcms.project.dto;

import com.hcms.project.entity.Address;
import com.hcms.project.entity.Contact;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateDoctorDTO {
	
  @NotBlank(message = "Doctor username must not be null")
	@Size(min = 8, max= 25, message = "Doctor username must be at least 8 and 25 maximum.")
	private String doctorUsername;	

  @NotBlank(message = "Doctor name should not be null.")
  @Size(min=2, max = 255, message = "Doctor name should not be empty.")
  private String doctorName;
  
  @NotBlank(message = "Qualification must not be empty")
  @Size(min =2,  message= "Qualification must be at least 2 charaters.")
  private String doctorQualification;
  
  @NotBlank(message = "Qualification must not be empty")
  @Size(min =2,  message= "Qualifiction must be at least 2 charaters.")
  private String specialist;
  
  @NotBlank(message = "Doctor Gender can't be empty")
  @Size(min=4, max=10, message = "Gender must be 4 to 10 letters")
  private String doctorGender;


  @NotNull
  private Contact contact;


  @NotNull
  private Address address;


public UpdateDoctorDTO(
		@NotBlank(message = "Doctor username must not be null") @Size(min = 8, max = 25, message = "Doctor username must be at least 8 and 25 maximum.") String doctorUsername,
		@NotBlank(message = "Doctor name should not be null.") @Size(min = 2, max = 255, message = "Doctor name should not be empty.") String doctorName,
		@NotBlank(message = "Qualification must not be empty") @Size(min = 2, message = "Qualification must be at least 2 charaters.") String doctorQualification,
		@NotBlank(message = "Qualification must not be empty") @Size(min = 2, message = "Qualifiction must be at least 2 charaters.") String specialist,
		@NotBlank(message = "Doctor Gender can't be empty") @Size(min = 4, max = 10, message = "Gender must be 4 to 10 letters") String doctorGender,
		@NotNull Contact contact, @NotNull Address address) {
	super();
	this.doctorUsername = doctorUsername;
	this.doctorName = doctorName;
	this.doctorQualification = doctorQualification;
	this.specialist = specialist;
	this.doctorGender = doctorGender;
	this.contact = contact;
	this.address = address;
}


public UpdateDoctorDTO() {
	super();
}


public String getDoctorUsername() {
	return doctorUsername;
}


public void setDoctorUsername(String doctorUsername) {
	this.doctorUsername = doctorUsername;
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


public Contact getContact() {
	return contact;
}


public void setContact(Contact contact) {
	this.contact = contact;
}


public Address getAddress() {
	return address;
}


public void setAddress(Address address) {
	this.address = address;
}


@Override
public String toString() {
	return "UpdateDoctorDTO [doctorUsername=" + doctorUsername + ", doctorName=" + doctorName + ", doctorQualification="
			+ doctorQualification + ", specialist=" + specialist + ", doctorGender=" + doctorGender + ", contact="
			+ contact + ", address=" + address + "]";
}
  
  
  

}
