package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface OvertimeApplicationService {

    List<OvertimeApplication> getAllOvertimeApplication();

    List<OvertimeApplication> getAllByManager(Staff manager);

    List<OvertimeApplication> getAllByStaff(Staff staff);

    OvertimeApplication getById(Long appId);

    void newApplication(OvertimeApplication app);

    void setApprovalStatus(OvertimeApplication app, String status, String remarks, Staff approver);

    OvertimeApplication newAPIApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment);

    List<OvertimeApplication> getAllPendingByManager(Staff manager1);

    List<OvertimeApplication>getListForReport(Long id, Long staffId);
}