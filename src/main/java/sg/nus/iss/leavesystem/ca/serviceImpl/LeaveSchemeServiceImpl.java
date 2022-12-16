package sg.nus.iss.leavesystem.ca.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;

@Service
public class LeaveSchemeServiceImpl implements LeaveSchemeService {
	@Autowired
	LeaveSchemeRepository leaveSchemeRepository;

	public List<LeaveScheme> getAllLeaveScheme() {
		return leaveSchemeRepository.findAll();
	}

	public LeaveScheme getLeaveSchemeByID(Long id) {
		return leaveSchemeRepository.findById(id).orElse(null);
	}

	@Override
	public LeaveScheme createLeaveScheme(LeaveScheme newLeaveScheme) {
		return leaveSchemeRepository.saveAndFlush(newLeaveScheme);
	}
}
