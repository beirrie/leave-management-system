package sg.nus.iss.leavesystem.ca.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.util.Util;
import sg.nus.iss.leavesystem.ca.model.*;
import sg.nus.iss.leavesystem.ca.service.LeaveTypeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

@Component
public class LeaveApplicationFormValidator implements Validator {

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private StaffService staffService;

    @Override
    public boolean supports(Class<?> clazz) {
        return LeaveApplicationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LeaveApplicationForm leaveForm = (LeaveApplicationForm) target;
        LocalDateTime endDate = Util.convertStringToDate(leaveForm.getEndDateStr());
        LocalDateTime startDate = Util.convertStringToDate(leaveForm.getStartDateStr());

        if (leaveForm.getLeaveType().getId().equals(3l) && leaveForm.getStartAMPM().isEmpty()) {
            errors.rejectValue("startAMPM", null, "Start AM/PM is required");
        }

        if (leaveForm.getLeaveType().getId().equals(3l) && leaveForm.getEndAMPM().isEmpty()) {
            errors.rejectValue("endAMPM", null, "End AM/PM is required");
        }

        if (endDate.isBefore(startDate)) {
            errors.rejectValue("endDateStr", null, "End date must not be less than Start Date!");
        }

        if (Util.isWeekend(startDate)) {
            errors.rejectValue("startDateStr", null, "Start date must not be on weekend!");
        }

        if (Util.isWeekend(endDate)) {
            errors.rejectValue("endDateStr", null, "End date must not be on weekend!");
        }

        if (leaveForm.getIsAbroad()) {
            if (leaveForm.getContactNumber() == "" || leaveForm.getContactNumber() == null) {
                errors.rejectValue("contactNumber", null, "Contact Details is mandatory!");
            }
        }
        Staff staff = staffService.findStaffByID(leaveForm.getStaffId());
        java.time.Duration duration = java.time.Duration.between(startDate, endDate);

        LeaveType leaveType = leaveTypeService.findById(leaveForm.getLeaveType().getId());

        // if(leaveType.getLeaveTypeName().equals("annual")){
        // {
        // if(duration.toDays()>staff.getAnnualLeaveBalance()){
        // errors.rejectValue("endDateStr", null, "The number of days exceeds your
        // balance");
        // }
        // }
        // if(leaveType.getLeaveTypeName().equals("medical")){
        // if(duration.toDays()>staff.getMedicalLeaveBalance()){
        // errors.rejectValue("endDateStr", null, "The number of days exceeds your
        // balance");
        // }
        // }
        // if(leaveType.getLeaveTypeName().equals("compensation")){
        // if(duration.toDays()>staff.getCompensationLeaveBalence()){
        // errors.rejectValue("endDateStr", null, "The number of days exceeds your
        // balance");
        // }
        // }
        // }
        LeaveApplication leaveApp = new LeaveApplication();
        leaveApp.setStartDate(startDate);
        leaveApp.setEndDate(endDate);
        double selectedDuration = Double.parseDouble(leaveApp.getDuration());
        boolean sameStartEndDate = leaveForm.getStartDateStr().equals(leaveForm.getEndDateStr());
        if (leaveForm.getStartAMPM().equals("PM")) {
            selectedDuration -= 0.5;
        }
        if (leaveForm.getEndAMPM().equals("AM")) {
            selectedDuration -= 0.5;
        }
        if (leaveForm.getLeaveType().getId().equals(3l) && selectedDuration > staff.getCompensationLeaveBalence()) {
            errors.rejectValue("endDateStr", null, "The number of days exceeds your balance");
        }
    }
}