package sg.nus.iss.leavesystem.ca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

@Service
public interface LeaveApplicationService {

	List<LeaveApplication> getAllLeaveApplications();
	List<LeaveApplication> getStaffLeavesByManager(Staff manager);
	List<LeaveApplication> getStaffLeavesByManagerAndStatus(Staff manager,String status);
	List<LeaveApplication> getStaffLeavesById(Long staffId);
	List<LeaveApplication> getStaffLeavesByIdAndStatus(Long staffId,String status);
	LeaveApplication getLeaveById(Long leaveId);
	
	void setApprovalStatus(LeaveApplication app, String status, String remarks, Staff approver);
	
}
