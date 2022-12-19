package sg.nus.iss.leavesystem.ca.service_implementation;

import jakarta.transaction.Transactional;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OvertimeApplicationServiceImpl implements OvertimeApplicationService {

    @Autowired
    OverTimeApplicationRepository otRepo;

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
        // TODO: increase leave according to amount of overtime
        otRepo.save(app);
    }

    @Override
    public void newAPIApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment) {

        OvertimeApplication newOT = new OvertimeApplication(employee, OT_Date, hours, employeeComment);
        otRepo.saveAndFlush(newOT);
    }

    @Override
    public List<OvertimeApplication> getAllPendingByManager(Staff manager) {

        String status1 = "Applied";
        String status2 = "Updated";

//        List<OvertimeApplication> appliedList =
//                otRepo.findByStatus(status1);
//        List<OvertimeApplication> updatedList =
//                otRepo.findByStatus(status2);

        List<OvertimeApplication> appliedList =
                otRepo.findByEmployee_Manager_IdAndApplicationStatus(manager.getId(), "Approved");
        List<OvertimeApplication> updatedList =
                otRepo.findByEmployee_Manager_IdAndApplicationStatus(manager.getId(), "Updated");

        List<OvertimeApplication> combinedList = new ArrayList<>();

        combinedList.addAll(appliedList);
        combinedList.addAll(updatedList);

        return combinedList;
    }
}
