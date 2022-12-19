package sg.nus.iss.leavesystem.ca.service_implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;

@Service
@Transactional
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
        leaveAppList = getAllLeaveApplications().stream()
                .filter(leave -> leave.getEmployeeManager().getId() == manager.getId())
                .collect(Collectors.toList());
        return leaveAppList;
    }

    @Override
    public List<LeaveApplication> getStaffLeavesByManagerAndStatus(Staff manager, String status) {
        List<LeaveApplication> leaveAppList = new ArrayList<>();
        leaveAppList = getAllLeaveApplications().stream()
                .filter(leave -> leave.getEmployeeManager().getId() == manager.getId()
                        && status.contains(leave.getApplicationStatus()))
                .collect(Collectors.toList());
        return leaveAppList;
    }

    @Override
    public List<LeaveApplication> getStaffLeavesById(Long staffId) {
        List<LeaveApplication> leaveAppList = new ArrayList<>();
        leaveAppList = getAllLeaveApplications().stream().filter(leave -> leave.getEmployee().getId() == staffId)
                .collect(Collectors.toList());
        return leaveAppList;
    }

    @Override
    public List<LeaveApplication> getStaffLeavesByIdAndStatus(Long staffId, String status) {
        List<LeaveApplication> leaveAppList = new ArrayList<>();
        leaveAppList = getAllLeaveApplications().stream().filter(
                leave -> leave.getEmployee().getId() == staffId && status.contains(leave.getApplicationStatus()))
                .collect(Collectors.toList());
        return leaveAppList;
    }

    @Override
    public LeaveApplication getLeaveById(Long leaveId) {
        return leaveAppRepo.getReferenceById(leaveId);
    }

    @Override
    public void setApprovalStatus(LeaveApplication app, String status,
            String remarks, Staff approver) {
        app.setApplicationStatus(status);
        app.setMgrRemarks(remarks);
        app.setDateReviewed(LocalDateTime.now());
        app.setEmployeeManager(approver);
        leaveAppRepo.saveAndFlush(app);
    }

    @Override
    public void CreateApplication(LeaveApplication leaveApplication) {
        this.leaveAppRepo.save(leaveApplication);
    }

    @Override
    public List<LeaveApplication> GetByStaffId(long staffId) {
        return this.leaveAppRepo.FindByUserId(staffId);
    }

    @Override
    public Optional<LeaveApplication> GetById(long id) {
        return this.leaveAppRepo.findById(id);
    }

    @Override
    public void UpdateApplication(LeaveApplication leaveApplication) {
        this.leaveAppRepo.save(leaveApplication);
    }

    @Override
    public void DeleteLeave(LeaveApplication leaveApplication) {
        this.leaveAppRepo.delete(leaveApplication);
    }

    @Override
    public List<LeaveApplication> getAllPendingByManager(Staff manager) {

        List<LeaveApplication> appliedList = getStaffLeavesByManagerAndStatus(manager, "Applied");
        List<LeaveApplication> updatedList = getStaffLeavesByManagerAndStatus(manager, "Updated");

        List<LeaveApplication> combinedList = new ArrayList<>();

        combinedList.addAll(appliedList);
        combinedList.addAll(updatedList);

        return combinedList;
    }


	@Override
	public List<LeaveApplication> getOverlapLeavesWithCurrentStaff(LeaveApplication leaveApp, Staff manager) {
		
		LocalDateTime currStaffStartDate = leaveApp.getStartDate();
		LocalDateTime currStaffEndDate = leaveApp.getEndDate(); 
		
		List<LeaveApplication> leaveAppsOverLap =  getStaffLeavesByManager(manager).stream()
				.filter(otherStaff-> otherStaff.getEndDate().isAfter(currStaffStartDate))
				.filter(otherStaff-> otherStaff.getEndDate().isEqual(currStaffStartDate))
				.filter(otherStaff-> otherStaff.getStartDate().isBefore(currStaffEndDate))
				.filter(otherStaff-> otherStaff.getStartDate().isEqual(currStaffEndDate))
				.collect(Collectors.toList());
		return leaveAppsOverLap;
	}



    
}
