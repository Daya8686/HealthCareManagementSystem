package com.hcms.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctordetails")
public class Doctor //extends Users 
{
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;
    
//    @NotBlank(message = "Doctor username must not be null")
//	@Size(min = 8, max= 25, message = "Doctor username must be at least 8 and 25 maximum.")
	@Column(nullable = false, unique = true)
	private String doctorUsername;
	
//	@NotBlank(message = "Password colum must be filled!")
//	@Size(min = 2, max= 25, message = "Password must be at least 8 and 25 maximum.")
	@Column(nullable = false)
	private String doctorPassword;
	

//    @NotBlank(message = "Doctor name should not be null.")
//    @Size(min=2, max = 255, message = "Doctor name should not be empty.")
	@Column(nullable = false)
    private String doctorName;
    
//    @NotBlank(message = "Doctor email should not be null.")
//    @Email(message = "Doctor email should be valid.")
//	@Column(nullable = false, unique = true)
//    private String doctorEmail;
    
//    @NotBlank(message = "Qualification must not be empty")
//    @Size(min =2,  message= "Qualification must be at least 2 charaters.")
	@Column(nullable = false)
    private String doctorQualification;
    
//    @NotBlank(message = "Doctor Gender can't be empty")
//    @Size(min=4, max=10, message = "Gender must be 4 to 10 letters")
	@Column(nullable = false)
	private String specialist;
	
	@Column(nullable = false)
    private String doctorGender;

    @OneToOne
    @JoinColumn(name = "contact_id", nullable = false)
  //  @NotNull
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    //@NotNull
    private Address address;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;
    
    @OneToMany(mappedBy = "doctor")
    private List<Report> reports;
    
    private String role="DOCTOR";
    
    
    

	public Doctor() {
		super();
	}




	public Doctor(Long doctorId, String doctorUsername, String doctorPassword, String doctorName,
			String doctorQualification, String specialist, String doctorGender, Contact contact, Address address,
			List<Appointment> appointments, List<Report> reports, String role) {
		super();
		this.doctorId = doctorId;
		this.doctorUsername = doctorUsername;
		this.doctorPassword = doctorPassword;
		this.doctorName = doctorName;
		this.doctorQualification = doctorQualification;
		this.specialist = specialist;
		this.doctorGender = doctorGender;
		this.contact = contact;
		this.address = address;
		this.appointments = appointments;
		this.reports = reports;
		this.role = role;
	}




	public Long getDoctorId() {
		return doctorId;
	}




	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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




	public List<Appointment> getAppointments() {
		return appointments;
	}




	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}




	public List<Report> getReports() {
		return reports;
	}




	public void setReports(List<Report> reports) {
		this.reports = reports;
	}




	public String getRole() {
		return role;
	}




	public void setRole(String role) {
		this.role = role;
	}




	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorUsername=" + doctorUsername + ", doctorPassword="
				+ doctorPassword + ", doctorName=" + doctorName + ", doctorQualification=" + doctorQualification
				+ ", specialist=" + specialist + ", doctorGender=" + doctorGender + ", contact=" + contact
				+ ", address=" + address + ", role=" + role + "]";
	}




	




}