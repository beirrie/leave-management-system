package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;

import java.util.List;

@Service
public interface OvertimeApplicationService {

    List<OvertimeApplication> getAllOvertimeApplication();

    void newApplication(OvertimeApplication app);
    void setApprovalStatus(OvertimeApplication app, int status,
                           String remarks, Long approverId);
}