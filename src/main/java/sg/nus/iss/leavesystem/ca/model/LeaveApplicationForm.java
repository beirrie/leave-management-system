package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDateTime;

import sg.nus.iss.leavesystem.ca.util.Util;

public class LeaveApplicationForm {

    public LeaveApplicationForm() {
        this.startDateStr = Util.convertDateToString(startDate);
        this.endDateStr = Util.convertDateToString(endDate);
    }

    private long id;

    private long staffId;

    private LeaveType leaveType;

    private String additionalComments;

    private Staff coveringStaff;

    private String contactNumber;

    private String applicationStatus = "New";

    private Boolean isAbroad;

    private LocalDateTime startDate = LocalDateTime.now();

    private String startAMPM = "AM";

    private String endAMPM = "PM";

    private String startDateStr;

    private LocalDateTime endDate = LocalDateTime.now();

    private String endDateStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    private String previousDuration;

    public Long getPreviousLeaveTypeId() {
        return previousLeaveTypeId;
    }

    public void setPreviousLeaveTypeId(Long previousLeaveTypeId) {
        this.previousLeaveTypeId = previousLeaveTypeId;
    }

    private Long previousLeaveTypeId;

    public String getPreviousDuration() {
        return previousDuration;
    }

    public void setPreviousDuration(String previousDuration) {
        this.previousDuration = previousDuration;
    }

    public String getStartAMPM() {
        return startAMPM;
    }

    public void setStartAMPM(String startAMPM) {
        this.startAMPM = startAMPM;
    }

    public String getEndAMPM() {
        return endAMPM;
    }

    public void setEndAMPM(String endAMPM) {
        this.endAMPM = endAMPM;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        this.startDateStr = Util.convertDateToString(startDate);
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        this.endDateStr = Util.convertDateToString(endDate);
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
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

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Boolean getIsAbroad() {
        return isAbroad;
    }

    public void setIsAbroad(Boolean isAbroad) {
        this.isAbroad = isAbroad;
    }
}