package sg.nus.iss.leavesystem.ca.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Staff {
	
	//Attribute
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long id;

	@Column(columnDefinition = "nvarchar(50) not null")
	public String firstName;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	public String lastName;
	
	@Column(columnDefinition = "nvarchar(255) not null")
	public String emailAdd;
	
	@ManyToOne
	@JoinColumn(name="manager_Id", referencedColumnName="id")
	public Staff manager;
	
	@OneToMany(mappedBy="manager")
	public Set<Staff> subordinates = new HashSet<>();
	
	@OneToOne
	public LeaveScheme leaveScheme;
	
	@OneToMany(mappedBy="employee")
	public List<LeaveApplication> leaveApplicationRecords= new ArrayList<>();
	
	@OneToMany(mappedBy="employee")
	public List<OvertimeApplication> overtimeApplicationRecords= new ArrayList<>();
	
	public double annualLeaveBalance;
	
	public double medicalLeaveBalance;
	
	public double compensationLeaveBalence;
	
	@OneToOne
	private User user;
	
	//Constructor
	
	public Staff() {}

	public Staff(String firstName, String lastName, String emailAdd, Staff manager, Set<Staff> subordinates,
			LeaveScheme leaveScheme, List<LeaveApplication> leaveApplicationRecords,
			List<OvertimeApplication> overtimeApplicationRecords, double annualLeaveBalance, double medicalLeaveBalance,
			double compensationLeaveBalence, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.manager = manager;
		this.subordinates = subordinates;
		this.leaveScheme = leaveScheme;
		this.leaveApplicationRecords = leaveApplicationRecords;
		this.overtimeApplicationRecords = overtimeApplicationRecords;
		this.annualLeaveBalance = annualLeaveBalance;
		this.medicalLeaveBalance = medicalLeaveBalance;
		this.compensationLeaveBalence = compensationLeaveBalence;
		this.user = user;
	}
	
	//Method

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Staff getManager() {
		return manager;
	}

	public void setManager(Staff manager) {
		this.manager = manager;
	}

	public Set<Staff> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(Set<Staff> subordinates) {
		this.subordinates = subordinates;
	}

	public LeaveScheme getLeaveScheme() {
		return leaveScheme;
	}

	public void setLeaveScheme(LeaveScheme leaveScheme) {
		this.leaveScheme = leaveScheme;
	}

	public List<LeaveApplication> getLeaveApplicationRecords() {
		return leaveApplicationRecords;
	}

	public void setLeaveApplicationRecords(List<LeaveApplication> leaveApplicationRecords) {
		this.leaveApplicationRecords = leaveApplicationRecords;
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

	public double getCompensationLeaveBalence() {
		return compensationLeaveBalence;
	}

	public void setCompensationLeaveBalence(double compensationLeaveBalence) {
		this.compensationLeaveBalence = compensationLeaveBalence;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}