package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String firstName;
	public String lastName;
	public String emailAdd;
//	public Staff manager;
	@OneToOne
	@JoinColumn(name = "leave_scheme_id")
	public LeaveScheme leaveScheme;
//	public List<LeaveApplication> leaveApplicationRecords;
	@OneToMany(mappedBy = "employee")
	public List<OvertimeApplication> overtimeApplicationRecords;
	public double annualLeaveBalance;
	public double medicalLeaveBalance;
	public double compensationLeaveBalance;

	public Staff() {
	}

	public Staff(String firstName, String lastName, String emailAdd,
				 LeaveScheme leaveScheme) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.leaveScheme = leaveScheme;
		this.annualLeaveBalance = leaveScheme.getAnnualLeaveEntitlement();
		this.medicalLeaveBalance = leaveScheme.getMedicalLeaveEntitlement();
		this.compensationLeaveBalance = 0;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public LeaveScheme getLeaveScheme() {
		return leaveScheme;
	}

	public void setLeaveScheme(LeaveScheme leaveScheme) {
		this.leaveScheme = leaveScheme;
	}

	public List<OvertimeApplication> getOvertimeApplicationRecords() {
		return overtimeApplicationRecords;
	}

	public void setOvertimeApplicationRecords(List<OvertimeApplication> overtimeApplicationRecords) {
		this.overtimeApplicationRecords = overtimeApplicationRecords;
	}

	public double getAnnualLeaveBalance() {
		return annualLeaveBalance;
	}

	public void setAnnualLeaveBalance(double annualLeaveBalance) {
		this.annualLeaveBalance = annualLeaveBalance;
	}

	public double getMedicalLeaveBalance() {
		return medicalLeaveBalance;
	}

	public void setMedicalLeaveBalance(double medicalLeaveBalance) {
		this.medicalLeaveBalance = medicalLeaveBalance;
	}

	public double getCompensationLeaveBalance() {
		return compensationLeaveBalance;
	}

	public void setCompensationLeaveBalance(double compensationLeaveBalance) {
		this.compensationLeaveBalance = compensationLeaveBalance;
	}
}