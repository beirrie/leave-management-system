package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;

@Service
public class LeaveSchemeServiceImpl implements LeaveSchemeService{
	
	@Resource
	public LeaveSchemeRepository _leaveSchemeRepo;
	
	@Override
	public List<LeaveScheme> getAllLeaveScheme(){
		
		return _leaveSchemeRepo.findAll();
	}
	
	@Override
	public LeaveScheme createLeaveScheme(LeaveScheme newLeaveScheme) {
		return _leaveSchemeRepo.saveAndFlush(newLeaveScheme);
	}

	@Override
	public LeaveScheme getLeaveSchemeByID(Long leaveSchemeID) {
		// TODO Auto-generated method stub
		return _leaveSchemeRepo.findById(leaveSchemeID).orElse(null);
	}
}
