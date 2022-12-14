package sg.nus.iss.leavesystem.ca.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Staff {
	
	public String id;
	public String firstName;
	public String lastName;
	public String emailAdd;
	
	@ManyToOne
	@JoinColumn(name="manager_Id", referencedColumnName="id")
	public Staff manager;
	
	@OneToMany(mappedBy="manager")
	public List<Staff> subordinates = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="leaveScheme", referencedColumnName="id")
	public LeaveScheme leaveScheme;
	
	@OneToMany(mappedBy="employee")
	public List<LeaveApplication> leaveApplicationRecords= new ArrayList<>();
	
	@OneToMany(mappedBy="employee")
	public List<OvertimeApplication> overtimeApplicationRecords= new ArrayList<>();
	
	public double annualLeaveBalance;
	
	public double medicalLeaveBalance;
	
	public double compensationLeaveBalence;
	
}