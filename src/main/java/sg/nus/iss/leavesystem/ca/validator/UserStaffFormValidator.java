package sg.nus.iss.leavesystem.ca.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

@Component
public class UserStaffFormValidator implements Validator {

	@Autowired
	private StaffService staffService;

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
		List<String> roles = userStaffForm.getRoles().stream().map(r -> r.getRoleName().toLowerCase()).toList();
		boolean isManager = roles.contains("manager");
		boolean isEmployee = roles.contains("employee");

		if (!isManager) {
			Staff staff = staffService.findStaffByID(userStaffForm.getStaffId());
			if (staff.getSubordinates().size() != 0) {
				errors.rejectValue("roles", "error.roles",
						"Manager with subordinates cannot be set to an employee role.");
			}
		}

		if (isManager && isEmployee) {
			errors.rejectValue("roles", "error.roles", "Employee and Manager cannot be selected together.");
		}

	}

}
