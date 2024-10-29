package com.hcms.project.dto;

public class DoctorDTO {

	private Long doctorId;

	private String doctorName;

	private String doctorQualification;

	private String specialist;

	private String doctorGender;

	

	public DoctorDTO(Long doctorId, String doctorName, String doctorQualification, String specialist,
			String doctorGender) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorQualification = doctorQualification;
		this.specialist = specialist;
		this.doctorGender = doctorGender;
		
	}

	public DoctorDTO() {
		super();
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
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

	

	@Override
	public String toString() {
		return "DoctorDTO [doctorId=" + doctorId + ", doctorName=" + doctorName + ", doctorQualification="
				+ doctorQualification + ", specialist=" + specialist + ", doctorGender=" + doctorGender + 
				 "]";
	}
	
	

}
