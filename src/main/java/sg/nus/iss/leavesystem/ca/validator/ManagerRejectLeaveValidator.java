package sg.nus.iss.leavesystem.ca.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leavesystem.ca.model.dto.LeaveApprovalDTO;

@Component
public class ManagerRejectLeaveValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return LeaveApprovalDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveApprovalDTO rejectedLeaveApp = (LeaveApprovalDTO)target;
		if(rejectedLeaveApp.getApplicationStatus().equalsIgnoreCase("Rejected") && rejectedLeaveApp.getApproverRemark().isBlank()) {
			System.out.println("Application status" + rejectedLeaveApp.getApplicationStatus());
			System.out.println("getApproverRemarks " + rejectedLeaveApp.getApproverRemark());
			System.out.println("validate before target");
			errors.rejectValue("approverRemark", "approverRemark.blank", "Approver Remarks is required for leave application rejection");
		}
		
	}


	
}
