package sg.nus.iss.leavesystem.ca.service_implementation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;

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
}
