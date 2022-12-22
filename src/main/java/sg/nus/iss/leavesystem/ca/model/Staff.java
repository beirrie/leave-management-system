package sg.nus.iss.leavesystem.ca.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "employees")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Staff {

	// Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "nvarchar(50) not null")
	private String firstName;

	@Column(columnDefinition = "nvarchar(50) not null")
	private String lastName;

	@Transient
	private String name;
	@Transient
	public String getName() {
		return lastName+" "+firstName;
	}
	
	@Column(columnDefinition = "nvarchar(255) not null")
	private String emailAdd;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "manager_Id", referencedColumnName = "id")
	@JsonBackReference
	private Staff manager;

	@OneToMany(fetch= FetchType.LAZY,mappedBy = "manager")
	@JsonManagedReference
	private Set<Staff> subordinates = new HashSet<>();

	@OneToOne
	private LeaveScheme leaveScheme;

	@OneToMany(mappedBy = "employee")
	private List<LeaveApplication> leaveApplicationRecords = new ArrayList<>();

	@OneToMany(mappedBy = "employee")
	private List<OvertimeApplication> overtimeApplicationRecords = new ArrayList<>();

	@OneToMany(mappedBy = "coveringStaff")
	private List<LeaveApplication> coveringLeaves = new ArrayList<>();

	private double annualLeaveBalance;

	private double medicalLeaveBalance;

	private double compensationLeaveBalence;

	@OneToOne
	private User user;

	private double accumulated_OT_Hours;

	public boolean isActive = true;

	// Constructor

	public Staff() {
	}

	public Staff(String firstName, String lastName, String emailAdd, LeaveScheme leaveScheme, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.leaveScheme = leaveScheme;
		this.annualLeaveBalance = leaveScheme.getAnnualLeaveEntitlement();
		this.medicalLeaveBalance = leaveScheme.getMedicalLeaveEntitlement();
		this.compensationLeaveBalence = 0;
		this.user = user;
	}

	// Method

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
	
	@JsonBackReference
	public List<LeaveApplication> getLeaveApplicationRecords() {
		return leaveApplicationRecords;
	}

	@JsonBackReference
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

	public double getAccumulated_OT_Hours() {
		return accumulated_OT_Hours;
	}

	public void setAccumulated_OT_Hours(double accumulated_OT_Hours) {
		this.accumulated_OT_Hours = accumulated_OT_Hours;
	}

	public List<LeaveApplication> getCoveringLeaves() {
		return coveringLeaves;
	}

	public void setCoveringLeaves(List<LeaveApplication> coveringLeaves) {
		this.coveringLeaves = coveringLeaves;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void deductLeave(LeaveApplication leaveApplication){
		double duration = Double.parseDouble(leaveApplication.getDuration());
		if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("annual")){
			annualLeaveBalance-= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("medical")){
			medicalLeaveBalance-= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("compensation")){
			compensationLeaveBalence-= duration;
		}
	}

	public void reinstateLeaveBalance(LeaveApplication leaveApplication){
		double duration = Double.parseDouble(leaveApplication.getDuration());
		if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("annual")){
			annualLeaveBalance+= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("medical")){
			medicalLeaveBalance+= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("compensation")){
			compensationLeaveBalence+= duration;
		} 
	}

	public void reinstatePreviousLeaveBalance(LeaveApplication leaveApplication,String durationStr){
		double duration = Double.parseDouble(durationStr);
		if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("annual")){
			annualLeaveBalance+= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("medical")){
			medicalLeaveBalance+= duration;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("compensation")){
			compensationLeaveBalence+= duration;
		} 
	}

	public Boolean isLeaveBalanceEnough(LeaveApplication leaveApplication)
	{
		double duration = Double.parseDouble(leaveApplication.getDuration());
		if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("annual")){
			if(duration > annualLeaveBalance) return false;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("medical")){
			if(duration > medicalLeaveBalance) return false;
		}else if(leaveApplication.getTypeOfLeave().getLeaveTypeName().toLowerCase().equals("compensation")){
			if(duration > compensationLeaveBalence) return false;
		}
		return true;
	}
}