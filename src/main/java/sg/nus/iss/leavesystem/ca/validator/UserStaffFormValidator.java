package sg.nus.iss.leavesystem.ca.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;

@Component
public class UserStaffFormValidator implements Validator {

	@Autowired
	private LeaveSchemeService leaveSchemeService;

	@Override
	public boolean supports(Class<?> clazz) {
		return UserStaffForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserStaffForm userStaffForm = (UserStaffForm) target;

		double medicalLeaveBalance = userStaffForm.getMedicalLeaveBalance();
		LeaveScheme leaveScheme = leaveSchemeService
				.getLeaveSchemeByID(Long.parseLong(userStaffForm.getLeaveSchemeId()));
		if (medicalLeaveBalance > leaveScheme.getMedicalLeaveEntitlement()) {
			errors.rejectValue("medicalLeaveBalance", "error.medicalLeaveBalance",
					"Number cannot be more than " + leaveScheme.getMedicalLeaveEntitlement());
		}

		double annualLeaveBalance = userStaffForm.getAnnualLeaveBalance();
		if (annualLeaveBalance > leaveScheme.getAnnualLeaveEntitlement()) {
			errors.rejectValue("annualLeaveBalance", "error.annualLeaveBalance",
					"Number cannot be more than " + leaveScheme.getAnnualLeaveEntitlement());
		}
	}

}
