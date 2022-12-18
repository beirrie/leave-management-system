package sg.nus.iss.leavesystem.ca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    @Autowired
    LeaveApplicationRepository leaveApplicationRepository;

    @Override
    public void CreateApplication(LeaveApplication leaveApplication) {
        this.leaveApplicationRepository.save(leaveApplication);
    }

    @Override
    public List<LeaveApplication> GetByStaffId(long staffId) {
        return this.leaveApplicationRepository.FindByUserId(staffId);
    }

    @Override
    public Optional<LeaveApplication> GetById(long id) {
        return this.leaveApplicationRepository.findById(id);
    }

    @Override
    public void UpdateApplication(LeaveApplication leaveApplication) {
        this.leaveApplicationRepository.save(leaveApplication);
    }

    @Override
    public void DeleteLeave(LeaveApplication leaveApplication) {
        this.leaveApplicationRepository.delete(leaveApplication);
    }
    
}