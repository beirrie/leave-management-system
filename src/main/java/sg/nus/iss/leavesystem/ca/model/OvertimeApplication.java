package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="overtime_records")
public class OvertimeApplication {
	
	public String id; //
	
	@ManyToOne
	@JoinColumn(name="employee_Id", referencedColumnName="id")
	public Staff employee;
	
	public LocalDateTime startDateTime; //
	public LocalDateTime endDateTime; //
	public String employeeComment; //
	public LocalDateTime appliedDateTime; //
	public String approvalStatus; //
	
	public String managerRemarks;//
	public LocalDate dateApplicationReviewed; //
	
}
