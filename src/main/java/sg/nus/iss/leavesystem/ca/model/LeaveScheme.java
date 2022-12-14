package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class LeaveScheme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	
	
	public String employmentScheme;
	
	public double annualLeaveEntitlement;
	public double medicalLeaveEntitlement;
	
	
	@OneToOne(mappedBy = "leaveScheme")
	public Staff staff;
	
	public LeaveScheme(String employmentScheme, double annualLeaveEntitlement, double medicalLeaveEntitlement) {
		super();
		this.employmentScheme = employmentScheme;
		this.annualLeaveEntitlement = annualLeaveEntitlement;
		this.medicalLeaveEntitlement = medicalLeaveEntitlement;
	}

	
}