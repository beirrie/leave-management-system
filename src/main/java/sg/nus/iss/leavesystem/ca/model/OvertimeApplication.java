package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="overtime_records")
public class OvertimeApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //
	
	@ManyToOne
	@JoinColumn(name="employee_Id", referencedColumnName="id")
	private Staff employee;
	
	private LocalDateTime date_OT; //
	
	private double hours_OT; //
	
	private String employeeComment; //
	
	private LocalDateTime appliedDateTime; //
	
	private String applicationStatus; //Previously it was an integer. Change it to String?
	
	@ManyToOne
	@JoinColumn(name="approverId", referencedColumnName="id")
	private Staff employeeManager;
	
	private String managerRemarks;//
	
	private LocalDateTime dateApplicationReviewed; //
	
	public OvertimeApplication() {
	}

	public OvertimeApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment) {
		this.employee = employee;
		this.date_OT = OT_Date;
		this.hours_OT = hours;
		this.employeeComment = employeeComment;
		this.appliedDateTime = LocalDateTime.now();
		this.applicationStatus = "Applied";
	}

	public Long getId() {
		return id;
	}

	public Staff getEmployee() {
		return employee;
	}

	public void setEmployee(Staff employee) {
		this.employee = employee;
	}

	public String getEmployeeComment() {
		return employeeComment;
	}

	public void setEmployeeComment(String employeeComment) {
		this.employeeComment = employeeComment;
	}

	public LocalDateTime getAppliedDateTime() {
		return appliedDateTime;
	}

	public void setAppliedDateTime(LocalDateTime appliedDateTime) {
		this.appliedDateTime = appliedDateTime;
	}
	

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getManagerRemarks() {
		return managerRemarks;
	}

	public void setManagerRemarks(String managerRemarks) {
		this.managerRemarks = managerRemarks;
	}

	public LocalDateTime getDateApplicationReviewed() {
		return dateApplicationReviewed;
	}

	public void setDateApplicationReviewed(LocalDateTime dateApplicationReviewed) {
		this.dateApplicationReviewed = dateApplicationReviewed;
	}

//	public Long getApproverId() {
//		return approverId;
//	}
//
//	public void setApproverId(Long approverId) {
//		this.approverId = approverId;
//	}
	
}
