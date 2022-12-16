package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="leave_schemes")
public class LeaveScheme {
	
	//Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String employmentScheme;
	
	private double annualLeaveEntitlement;
	
	private double medicalLeaveEntitlement;
	
	public boolean isActive = true;
	
	//Constructor
	
	public LeaveScheme() {
		
	}

	public LeaveScheme(String employmentScheme, double annualLeaveEntitlement, double medicalLeaveEntitlement) {
		this.employmentScheme = employmentScheme;
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
	}	
	
	//Method
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean getIsActive() {
		return isActive;
	}

	public void setActive(boolean status) {
		this.isActive = status;
	}
	
}
