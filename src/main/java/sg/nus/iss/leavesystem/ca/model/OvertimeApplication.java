package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OvertimeApplication {
	
	public String id; //
	public Staff employee;
	public LocalDateTime startDateTime; //
	public LocalDateTime endDateTime; //
	public String employeeComment; //
	public LocalDateTime appliedDateTime; //
	public String approvalStatus; //
	
	public String managerRemarks;//
	public LocalDate dateApplicationReviewed; //
	
}
