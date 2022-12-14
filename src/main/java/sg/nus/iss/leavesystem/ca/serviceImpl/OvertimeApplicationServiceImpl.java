package sg.nus.iss.leavesystem.ca.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.repository.OvertimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;

import java.util.List;

@Service
@Transactional
public class OvertimeApplicationServiceImpl implements OvertimeApplicationService {

    @Autowired
    OvertimeApplicationRepository overtimeRepo;

    @Override
    public List<OvertimeApplication> getAllOvertimeApplication() {
        return overtimeRepo.findAll();
    }

    @Override
    public void newApplication(OvertimeApplication app) {
        overtimeRepo.save(app);
    }
    @Override
    public void setApprovalStatus(OvertimeApplication app, int status,
                                  String remarks, Long approverId) {

        app.setApprovalStatus(status);
        app.setManagerRemarks(remarks);
        app.setApproverId(approverId);
        overtimeRepo.save(app);
    }
}
