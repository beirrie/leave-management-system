package sg.nus.iss.leavesystem.ca.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;

@Service
public class OvertimeAppicationServiceImpl implements OvertimeApplicationService {
	
	@Resource
	OverTimeApplicationRepository OTrepo;
//	@Override
//	public List<OvertimeApplication> getAllOvertimeApplication() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void newApplication(OvertimeApplication app) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void setApprovalStatus(OvertimeApplication app, int status, String remarks, Long approverId) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void newAPIApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment) {
		
		OvertimeApplication newOT = new OvertimeApplication(employee, OT_Date, hours, employeeComment);
		OTrepo.saveAndFlush(newOT);
	}

}
