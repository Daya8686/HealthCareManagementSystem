package com.hcms.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@Table(name = "patientdetails")

public class Patient //extends Users 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;
	
	@Column(nullable = false, unique = true)
	private String patientUsername;
	

	@Column(nullable = false)
	private String patientPassword;
	


	@Column(nullable = false)
    private String patientName;
	

    private String patientBloodGroup;

    @Column(nullable = false)
    private String patientGender;

    @OneToOne
    @JoinColumn(name = "contact_id")
   // @NotNull
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "address_id")
   // @NotNull
    private Address address;

    @OneToMany
    @JoinColumn(name="patient_id")

    private List<Appointment> appointments;

    @OneToMany
    @JoinColumn(name="patient_id")
    private List<Report> reports;
    
    
    private final String role="PATIENT";
    
    

	public Patient() {
		super();
	}



	public Patient(Long patientId, String patientUsername, String patientPassword, String patientName,
			String patientBloodGroup, String patientGender, Contact contact, Address address,
			List<Appointment> appointments, List<Report> reports) {
		super();
		this.patientId = patientId;
		this.patientUsername = patientUsername;
		this.patientPassword = patientPassword;
		this.patientName = patientName;
		this.patientBloodGroup = patientBloodGroup;
		this.patientGender = patientGender;
		this.contact = contact;
		this.address = address;
		this.appointments = appointments;
		this.reports = reports;
	}



	public Long getPatientId() {
		return patientId;
	}



	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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



	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", patientUsername=" + patientUsername + ", patientPassword="
				+ patientPassword + ", patientName=" + patientName + ", patientBloodGroup=" + patientBloodGroup
				+ ", patientGender=" + patientGender + ", contact=" + contact + ", address=" + address
				+  ", role=" + role + "]";
	}



	
}