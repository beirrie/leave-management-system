package sg.nus.iss.leavesystem.ca.model;

import java.util.List;

public class Staff {
	
	public String id;
	public String firstName;
	public String lastName;
	public String emailAdd;
	public Staff manager;
	public LeaveScheme leaveScheme;
	public List<LeaveApplication> leaveApplicationRecords;
	public List<OvertimeApplication> overtimeApplicationRecords;
	public double annualLeaveBalance;
	public double medicalLeaveBalance;
	public double compensationLeaveBalence;
	
}