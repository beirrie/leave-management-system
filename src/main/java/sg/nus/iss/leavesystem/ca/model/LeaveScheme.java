package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LeaveScheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String employmentScheme;
	public double annualLeaveEntitlement;
	public double medicalLeaveEntitlement;

	public LeaveScheme() {
	}

	public LeaveScheme(String employmentScheme, double annualLeaveEntitlement, double medicalLeaveEntitlement) {
		this.employmentScheme = employmentScheme;
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
	}

	public Long getId() {
		return id;
	}

	public String getEmploymentScheme() {
		return employmentScheme;
	}

	public void setEmploymentScheme(String employmentScheme) {
		this.employmentScheme = employmentScheme;
	}

	public double getAnnualLeaveEntitlement() {
		return annualLeaveEntitlement;
	}

	public void setAnnualLeaveEntitlement(double annualLeaveEntitlement) {
		this.annualLeaveEntitlement = annualLeaveEntitlement;
	}

	public double getMedicalLeaveEntitlement() {
		return medicalLeaveEntitlement;
	}

	public void setMedicalLeaveEntitlement(double medicalLeaveEntitlement) {
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
	}
}
