package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;

public class LeaveApplication {
	
	public int id;//
	public Staff employee; //
	public LeaveType typeOfLeave; //
	public Boolean isAbroad; //
	public String contactNumber; //
	public Staff coveringStaff; //
	public LocalDate startDate;//
	public String startAM_or_PM;//
	public LocalDate endDate;//
	public String endAM_or_PM;//
	public String additionalComments;//
	public LocalDate applicationDate;//
	
	public Staff employeeManager;//
	public String approvalStatus;//
	public LocalDate dateReviewed;//
	public String mgrRemarks;//
}
