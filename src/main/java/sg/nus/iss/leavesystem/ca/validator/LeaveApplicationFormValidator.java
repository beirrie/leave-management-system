package sg.nus.iss.leavesystem.ca.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.util.Util;
import sg.nus.iss.leavesystem.ca.model.*;

@Component
public class LeaveApplicationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LeaveApplicationForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LeaveApplicationForm leaveForm = (LeaveApplicationForm) target;
        LocalDateTime endDate = Util.convertStringToDate(leaveForm.getEndDateStr());
        LocalDateTime startDate = Util.convertStringToDate(leaveForm.getStartDateStr());

        if (endDate.isBefore(startDate)) {
            errors.rejectValue("endDateStr", null, "End date must not be less than Start Date!");
        }

        if(Util.isWeekend(startDate)){
            errors.rejectValue("startDateStr", null, "Start date must not be on weekend!");
        }

        if(Util.isWeekend(endDate)){
            errors.rejectValue("endDateStr", null, "End date must not be on weekend!");
        }

        if(leaveForm.getIsAbroad()){
            if(leaveForm.getContactNumber() == "" || leaveForm.getContactNumber() == null)
            {
                errors.rejectValue("contactNumber", null, "Contact Details is mandatory!");
            }
        }
    }

}
