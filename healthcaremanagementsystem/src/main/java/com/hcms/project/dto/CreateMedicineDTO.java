package com.hcms.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateMedicineDTO {

	@NotBlank(message = "Medicine Name cannot be null.")
	@Size(min = 2, max = 255, message = "Medicine name must be at least 2 charaters")
	private String medicineName;

	@NotBlank(message = "Medicine Description cannot be null.")
	@Size(min = 2, max = 255, message = "Medicine Description must be at least 2 charaters")
	private String medicineDescription;

	public CreateMedicineDTO() {
		super();
	}

	public CreateMedicineDTO(
			@NotBlank(message = "Medicine Name cannot be null.") @Size(min = 2, max = 255, message = "Medicine name must be at least 2 charaters") String medicineName,
			@NotBlank(message = "Medicine Description cannot be null.") @Size(min = 2, max = 255, message = "Medicine Description must be at least 2 charaters") String medicineDescription) {
		super();
		this.medicineName = medicineName;
		this.medicineDescription = medicineDescription;
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
		return "CreateMedicineDTO [medicineName=" + medicineName + ", medicineDescription=" + medicineDescription + "]";
	}

}
