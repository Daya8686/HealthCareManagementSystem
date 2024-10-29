package com.hcms.project.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="medicine")
public class Medicine {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;

   @Column(nullable = false)
    private String medicineName;
    
    @Column(nullable = false)
    private String medicineDescription;

    @ManyToMany(mappedBy = "medicines")
    private List<Report> reports;
    
    
    

	public Medicine() {
		super();
	}
	

	public Medicine(Long medicineId, String medicineName, String medicineDescription, List<Report> reports) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineDescription = medicineDescription;
		this.reports = reports;
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineDescription="
				+ medicineDescription + ", reports=" + reports + "]";
	}
    
    
    
}
