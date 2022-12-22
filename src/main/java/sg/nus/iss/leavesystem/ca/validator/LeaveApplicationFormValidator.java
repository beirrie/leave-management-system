package sg.nus.iss.leavesystem.ca.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.LeaveApplicationForm;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.PublicHolidayService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.util.Util;

@Component
public class LeaveApplicationFormValidator implements Validator {

    @Autowired
    private PublicHolidayService publicHolidayService;
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
        Staff staff = staffService.findStaffByID(leaveForm.getStaffId());
        LeaveApplication leaveApp = new LeaveApplication();
        leaveApp.setStartDate(startDate);
        leaveApp.setEndDate(endDate);
        leaveApp.setStartAM_or_PM(leaveForm.getStartAMPM());
        leaveApp.setEndAM_or_PM(leaveForm.getEndAMPM());
        double selectedDuration = Double.parseDouble(leaveApp.getDuration());
        Util.phs = this.publicHolidayService.getAllPublicHolidays();

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

        if (Util.isPublicHoliday(startDate)) {
            errors.rejectValue("startDateStr", null, "Start date must not be on a holiday!");
        }

        if (Util.isPublicHoliday(endDate)) {
            errors.rejectValue("endDateStr", null, "End date must not be on a holiday!");
        }

        if (leaveForm.getIsAbroad()) {
            if (leaveForm.getContactNumber() == "" || leaveForm.getContactNumber() == null) {
                errors.rejectValue("contactNumber", null, "Contact Details is mandatory!");
            }
        }

        boolean isStartEndDateSame = leaveForm.getStartDateStr().equals(leaveForm.getEndDateStr());
        if (isStartEndDateSame && leaveForm.getStartAMPM().equals("PM") && leaveForm.getEndAMPM().equals("AM")) {
            errors.rejectValue("endDateStr", null, "End date must not be less than Start Date!");
        }

        if (leaveForm.getPreviousDuration() != null) {
            if (leaveForm.getLeaveType().getId().equals(3l) && selectedDuration > staff.getCompensationLeaveBalence()
                    + Double.parseDouble(leaveForm.getPreviousDuration())) {
                errors.rejectValue("endDateStr", null, "The number of days exceeds your balance");
            }
        } else {
            if (leaveForm.getLeaveType().getId().equals(3l) && selectedDuration > staff.getCompensationLeaveBalence()) {
                errors.rejectValue("endDateStr", null, "The number of days exceeds your balance");
            }
        }
    }
}