package sg.nus.iss.leavesystem.ca.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OvertimeApplicationServiceImpl implements OvertimeApplicationService {

    @Autowired
    OverTimeApplicationRepository otRepo;

    @Autowired
    StaffService staffService;

    @Override
    public List<OvertimeApplication> getAllOvertimeApplication() {

        return otRepo.findAll();
    }

    @Override
    public List<OvertimeApplication> getAllByManager(Staff manager) {

        return otRepo.findByManager(manager.getId());
    }

    @Override
    public List<OvertimeApplication> getAllByStaff(Staff staff) {

        return otRepo.findByStaff(staff.getId());
    }

    @Override
    public OvertimeApplication getById(Long appId) {

        return otRepo.getReferenceById(appId);
    }

    @Override
    public void newApplication(OvertimeApplication app) {

        otRepo.save(app);
    }

    @Override
    public void setApprovalStatus(OvertimeApplication app, String status,
            String remarks, Staff approver) {

        app.setApplicationStatus(status);
        app.setManagerRemarks(remarks);
        app.setApprover(approver);
        app.setDateApplicationReviewed(LocalDateTime.now());
        if(status.equalsIgnoreCase("Approved")) {
            staffService.modifyCompensationLeaveBalance(app.getEmployee(), app.getHours_OT());
        }
        otRepo.save(app);
    }

    @Override
    public OvertimeApplication newAPIApplication(Staff employee, LocalDateTime OT_Date, double hours,
            String employeeComment) {

        OvertimeApplication newOT = new OvertimeApplication(employee, OT_Date, hours, employeeComment);
        otRepo.saveAndFlush(newOT);
        return newOT;
    }

    @Override
    public List<OvertimeApplication> getAllPendingByManager(Staff manager) {

        List<OvertimeApplication> appliedList = otRepo.findByEmployee_Manager_IdAndApplicationStatus(manager.getId(),
                "Applied");
        List<OvertimeApplication> updatedList = otRepo.findByEmployee_Manager_IdAndApplicationStatus(manager.getId(),
                "Updated");

        List<OvertimeApplication> combinedList = new ArrayList<>();

        combinedList.addAll(appliedList);
        combinedList.addAll(updatedList);

        return combinedList;
    }

    @Override
    public List<OvertimeApplication> getListForReport(Long id, Long staffId) {

        if (staffId == 0L) {
            return otRepo.findByManager(id);
        } else {
            Staff staff = staffService.findStaffByID(staffId);
            return getAllByStaff(staff);
        }
    }
}
