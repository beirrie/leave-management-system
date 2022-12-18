package sg.nus.iss.leavesystem.ca.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;

public interface LeaveApplicationService {

    void CreateApplication(LeaveApplication leaveApplication);

    void UpdateApplication(LeaveApplication leaveApplication);

    List<LeaveApplication> GetByStaffId(long staffId);

    Optional<LeaveApplication> GetById(long id);

    void DeleteLeave(LeaveApplication leaveApplication);
}
