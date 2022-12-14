package sg.nus.iss.leavesystem.ca.serviceImpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;

import java.util.List;

@Service
@Transactional
public class LeaveSchemeServiceImpl implements LeaveSchemeService {

    @Autowired
    LeaveSchemeRepository leaveSchemeRepo;

    @Override
    public List<LeaveScheme> getAllLeaveScheme(){
        return leaveSchemeRepo.findAll();
    }
}
