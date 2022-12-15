package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="leave_applications")
public class LeaveApplication {
	
	//Attributes
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;//
	
	@ManyToOne
	@JoinColumn(name="employeeId", referencedColumnName="id")
	private Staff employee; //
	
	@OneToOne
	private LeaveType typeOfLeave; //
	
	private Boolean isAbroad; //
	
	private String contactNumber; //
	
	@ManyToOne
	@JoinColumn(name="workDissemination", referencedColumnName="id")
	private Staff coveringStaff; //
	
	private LocalDateTime startDate;//
	
	private String startAM_or_PM;//
	
	private LocalDateTime endDate;//
	
	private String endAM_or_PM;//
	
	private String additionalComments;//
	
	private LocalDate applicationDate;//
	
	private String applicationStatus;
	
	@ManyToOne
	@JoinColumn(name="mgrID", referencedColumnName="id")
	private Staff employeeManager;//
	
	
	private LocalDateTime dateReviewed;//
	
	private String mgrRemarks;//
	
	//Constructor
	
	public LeaveApplication() {};
	
	//Methods

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public LeaveType getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(LeaveType typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public Boolean getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(Boolean isAbroad) {
		this.isAbroad = isAbroad;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Staff getCoveringStaff() {
		return coveringStaff;
	}

	public void setCoveringStaff(Staff coveringStaff) {
		this.coveringStaff = coveringStaff;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public String getStartAM_or_PM() {
		return startAM_or_PM;
	}

	public void setStartAM_or_PM(String startAM_or_PM) {
		this.startAM_or_PM = startAM_or_PM;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public String getEndAM_or_PM() {
		return endAM_or_PM;
	}

	public void setEndAM_or_PM(String endAM_or_PM) {
		this.endAM_or_PM = endAM_or_PM;
	}

	public String getAdditionalComments() {
		return additionalComments;
	}

	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}

	public LocalDate getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDate applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Staff getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(Staff employeeManager) {
		this.employeeManager = employeeManager;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public LocalDateTime getDateReviewed() {
		return dateReviewed;
	}

	public void setDateReviewed(LocalDateTime dateReviewed) {
		this.dateReviewed = dateReviewed;
	}

	public String getMgrRemarks() {
		return mgrRemarks;
	}

	public void setMgrRemarks(String mgrRemarks) {
		this.mgrRemarks = mgrRemarks;
	}

	public Staff getEmployee() {
		return employee;
	}

	public void setEmployee(Staff employee) {
		this.employee = employee;
	}
	
	
}
