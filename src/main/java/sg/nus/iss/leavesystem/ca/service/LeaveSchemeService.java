package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;

import java.util.List;

@Service
public interface LeaveSchemeService {

    List<LeaveScheme> getAllLeaveScheme();
}