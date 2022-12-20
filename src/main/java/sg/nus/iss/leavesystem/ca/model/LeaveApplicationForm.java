package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDateTime;

import sg.nus.iss.leavesystem.ca.util.Util;

public class LeaveApplicationForm {

    public LeaveApplicationForm() {
        this.startDateStr = Util.convertDateToString(startDate);
        this.endDateStr = Util.convertDateToString(endDate);
    }

    private long leaveTypeId;

    private long staffId;

    private LeaveType leaveType;

    private String additionalComments;

    private Staff coveringStaff;

    private String contactNumber;

    private String applicationStatus = "New";

    private Boolean isAbroad;

    private LocalDateTime startDate = LocalDateTime.now();

    private String startAMPM;

    public long getLeaveTypeId() {
        return leaveTypeId;
    }

    public void setLeaveTypeId(long id) {
        this.leaveTypeId = id;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
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

    private String endAMPM;

    private String startDateStr;

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

    private LocalDateTime endDate = LocalDateTime.now();

    private String endDateStr;

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