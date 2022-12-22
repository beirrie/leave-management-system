package sg.nus.iss.leavesystem.ca.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.Staff;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
@Service
public interface LeaveApplicationService {

	List<LeaveApplication> getAllLeaveApplications();
	List<LeaveApplication> getStaffLeavesByManager(Staff manager);
	List<LeaveApplication> getStaffLeavesByManagerAndStatus(Staff manager,String status);
	List<LeaveApplication> getStaffLeavesById(Long staffId);
	List<LeaveApplication> getStaffLeavesByIdAndStatus(Long staffId,String status);
	LeaveApplication getLeaveById(Long leaveId);
	void setApprovalStatus(LeaveApplication app, String status, String remarks, Staff approver);
    void CreateApplication(LeaveApplication leaveApplication);
    void UpdateApplication(LeaveApplication leaveApplication);
    List<LeaveApplication> GetByStaffId(long staffId);
    Optional<LeaveApplication> GetById(long id);
    void DeleteLeave(LeaveApplication leaveApplication);
	List<LeaveApplication> getAllPendingByManager(Staff manager1);
	List<LeaveApplication> getOverlapLeavesWithCurrentStaff(LeaveApplication leaveApp,Staff manager);
	List<LeaveApplication> getListForReport(Long managerId, Long staffId, String leaveTypeName, String startPeriod,
											String endPeriod);
}
