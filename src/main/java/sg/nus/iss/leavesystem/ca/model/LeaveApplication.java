package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import sg.nus.iss.leavesystem.ca.util.Util;

@Entity
@Table(name = "leave_applications")
public class LeaveApplication {

	// Attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "employeeId", referencedColumnName = "id")
	@JsonBackReference
	private Staff employee;

	@ManyToOne(fetch = FetchType.LAZY)
	private LeaveType typeOfLeave;

	private Boolean isAbroad;

	private String contactNumber;

	@ManyToOne
	@JoinColumn(name = "workDissemination", referencedColumnName = "id")
	private Staff coveringStaff;

	private LocalDateTime startDate;

	private String startAM_or_PM = "AM";

	private LocalDateTime endDate;

	private String endAM_or_PM = "PM";

	private String additionalComments;

	private LocalDateTime applicationDate;

	private String applicationStatus;

	@ManyToOne
	@JoinColumn(name = "mgrID", referencedColumnName = "id")
	private Staff employeeManager;

	private LocalDateTime dateReviewed;

	private String mgrRemarks;

	// Constructor

	public LeaveApplication(Staff employee, LeaveType typeOfLeave, Boolean isAbroad, String contactNumber,
			Staff coveringStaff, LocalDateTime startDate, String startAM_or_PM, LocalDateTime endDate,
			String endAM_or_PM, String additionalComments, LocalDateTime applicationDate, String applicationStatus,
			Staff employeeManager, LocalDateTime dateReviewed, String mgrRemarks) {
		this.employee = employee;
		this.typeOfLeave = typeOfLeave;
		this.isAbroad = isAbroad;
		this.contactNumber = contactNumber;
		this.coveringStaff = coveringStaff;
		this.startDate = startDate;
		this.startAM_or_PM = startAM_or_PM;
		this.endDate = endDate;
		this.endAM_or_PM = endAM_or_PM;
		this.additionalComments = additionalComments;
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
		this.employeeManager = employeeManager;
		this.dateReviewed = dateReviewed;
		this.mgrRemarks = mgrRemarks;
	}

	public LeaveApplication() {
	}

	// Methods

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Staff getEmployee() {
		return employee;
	}

	public void setEmployee(Staff employee) {
		this.employee = employee;
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

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
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

	public String getPeriod() {
		return Util.convertDateToString(startDate) + " - " + Util.convertDateToString(endDate);
	}

	public double getBeforeDuration() {
		double durationInDay = 0;
		LocalDateTime tempDate = startDate;
		while (!tempDate.isAfter(endDate)) {
			durationInDay++;
			tempDate = tempDate.plusDays(1);
		}
		return durationInDay;
	}

	public String getDuration() {
		double beforeDurationInDay = getBeforeDuration();
		if (beforeDurationInDay > 14) {
			return String.valueOf(beforeDurationInDay);
		}
		double durationInDay = 0;
		LocalDateTime tempDate = startDate;
		while (!tempDate.isAfter(endDate)) {
			if (!Util.isWeekend(tempDate) && !Util.isPublicHoliday(tempDate)) {
				durationInDay++;
			}

			tempDate = tempDate.plusDays(1);
		}
		if (startAM_or_PM.equals("PM")) {
			durationInDay -= 0.5;
		}
		if (endAM_or_PM.equals("AM")) {
			durationInDay -= 0.5;
		}
		return String.valueOf(durationInDay);
	}
}