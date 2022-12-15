package sg.nus.iss.leavesystem.ca.service_implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
	
	@Autowired
	private LeaveApplicationRepository leaveAppRepo;
	
	public LeaveApplicationServiceImpl(LeaveApplicationRepository leaveAppRepo) {
		this.leaveAppRepo = leaveAppRepo;
	}

	@Override
	public List<LeaveApplication> getAllLeaveApplications() {
		return leaveAppRepo.findAll();
	}

	@Override
	public List<LeaveApplication> getStaffLeavesByManager(Staff manager) {
		List<LeaveApplication> leaveAppList = new ArrayList<>();
		leaveAppList = getAllLeaveApplications().stream().filter(leave-> leave.getEmployeeManager().getId() == manager.getId())
				.collect(Collectors.toList());
		return leaveAppList;
	}

	
	@Override
	public List<LeaveApplication> getStaffLeavesByManagerAndStatus(Staff manager,String status) {
		List<LeaveApplication> leaveAppList = new ArrayList<>();
		leaveAppList = getAllLeaveApplications().stream().filter(leave-> leave.getEmployeeManager().getId() == manager.getId() && status.contains(leave.getApplicationStatus()))
				.collect(Collectors.toList());
		return leaveAppList;
	}



	@Override
	public List<LeaveApplication> getStaffLeavesById(Long staffId) {
		List<LeaveApplication> leaveAppList = new ArrayList<>();
		leaveAppList = getAllLeaveApplications().stream().filter(leave-> leave.getEmployee().getId() == staffId)
				.collect(Collectors.toList());
		return leaveAppList;
	}

	@Override
	public List<LeaveApplication> getStaffLeavesByIdAndStatus(Long staffId, String status) {
		List<LeaveApplication> leaveAppList = new ArrayList<>();
		leaveAppList = getAllLeaveApplications().stream().filter(leave-> leave.getEmployee().getId() == staffId && status.contains(leave.getApplicationStatus()))
				.collect(Collectors.toList());
		return leaveAppList;
	}

	
	@Override
    public void setApprovalStatus(LeaveApplication app, String status,
                                  String remarks, Staff approver) {
        app.setApplicationStatus(status);
        app.setMgrRemarks(remarks);
        app.setEmployeeManager(approver);
        leaveAppRepo.saveAndFlush(app);
    }
}
