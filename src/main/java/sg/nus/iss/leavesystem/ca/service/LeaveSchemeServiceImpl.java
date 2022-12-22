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
		return _leaveSchemeRepo.findById(leaveSchemeID).orElse(null);
	}

	@Override
	public LeaveScheme updateLeaveScheme(String id, String schemeName, String annualLeave, String medicalLeave) {
		Long _lsID = Long.parseLong(id);
		LeaveScheme lsEdited = getLeaveSchemeByID(_lsID);
		lsEdited.setEmploymentScheme(schemeName);
		Double _lsAL = Double.parseDouble(annualLeave);
		Double _lsML = Double.parseDouble(medicalLeave);
		lsEdited.setAnnualLeaveEntitlement(_lsAL);
		lsEdited.setMedicalLeaveEntitlement(_lsML);
		return _leaveSchemeRepo.saveAndFlush(lsEdited);
	}

	@Override
	public void deactivateLeaveScheme(String id) {
		Long _lsID = Long.parseLong(id);
		LeaveScheme lsEdited = getLeaveSchemeByID(_lsID);
		System.out.println(lsEdited.getIsActive());
		if(lsEdited.getIsActive()) {
			lsEdited.setActive(false);
		}
		else if (!lsEdited.getIsActive()){
			lsEdited.setActive(true);
		}
		_leaveSchemeRepo.saveAndFlush(lsEdited);
	}
	
}
