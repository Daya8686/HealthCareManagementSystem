package com.hcms.project.dto;

public class MedicineDTO {

	private Long medicineId;

	private String medicineName;

	private String medicineDescription;

	public MedicineDTO(Long medicineId, String medicineName, String medicineDescription) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineDescription = medicineDescription;
	}

	public MedicineDTO() {
		super();
	}

	public Long getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineDescription() {
		return medicineDescription;
	}

	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
	}

	@Override
	public String toString() {
		return "MedicineDTO [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineDescription="
				+ medicineDescription + "]";
	}

}
