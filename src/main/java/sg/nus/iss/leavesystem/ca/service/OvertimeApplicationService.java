package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.util.List;

@Service
public interface OvertimeApplicationService {

    List<OvertimeApplication> getAllOvertimeApplication();
    List<OvertimeApplication> getAllByManager(Staff manager);
    List<OvertimeApplication> getAllByStaff(Staff staff);
    void newApplication(OvertimeApplication app);
    void setApprovalStatus(OvertimeApplication app, String status,
                           String remarks, Staff approver);

}