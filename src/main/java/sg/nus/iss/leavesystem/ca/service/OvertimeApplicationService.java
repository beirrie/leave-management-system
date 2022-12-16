package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.util.List;
import java.util.Optional;

@Service
public interface OvertimeApplicationService {

    List<OvertimeApplication> getAllOvertimeApplication();
    List<OvertimeApplication> getAllByManager(Staff manager);
    List<OvertimeApplication> getAllByStaff(Staff staff);
    OvertimeApplication getById(Long appId);
    void newApplication(OvertimeApplication app);
    void setApprovalStatus(OvertimeApplication app, String status,
                           String remarks, Staff approver);

}