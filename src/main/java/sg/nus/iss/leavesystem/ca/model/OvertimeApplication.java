package sg.nus.iss.leavesystem.ca.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class OvertimeApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id; //
	@ManyToOne
	@JoinColumn(name = "employee_id")
	public Staff employee;
	public LocalDateTime startDateTime; //
	public LocalDateTime endDateTime; //
	public String employeeComment; //
	public LocalDateTime appliedDateTime; //
	public int approvalStatus; //
	public Long approverId;
	public String managerRemarks;//
	public LocalDate dateApplicationReviewed; //

	public OvertimeApplication() {
	}

	public OvertimeApplication(Staff employee, LocalDateTime startDateTime, LocalDateTime endDateTime, String employeeComment) {
		this.employee = employee;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.employeeComment = employeeComment;
		this.appliedDateTime = LocalDateTime.now();
		this.approvalStatus = 0;
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

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
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

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getManagerRemarks() {
		return managerRemarks;
	}

	public void setManagerRemarks(String managerRemarks) {
		this.managerRemarks = managerRemarks;
	}

	public LocalDate getDateApplicationReviewed() {
		return dateApplicationReviewed;
	}

	public void setDateApplicationReviewed(LocalDate dateApplicationReviewed) {
		this.dateApplicationReviewed = dateApplicationReviewed;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}
}
