package sg.nus.iss.leavesystem.ca.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.util.StringToDateTimeYYYYMMDD;

@Controller
public class OTClaimController {
	
	@Autowired
	OvertimeApplicationService _OTApplicationService;
	
	@Autowired
	StaffService _StaffService;
	
	@GetMapping("/ApplyOT")
	public String applyOT(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);        
        Long staffID = userSession.getStaffId();
        Staff _targetStaff = _StaffService.FindByUserId(staffID);
        String managerName = _targetStaff.getManager().getName();
        Long managerID = _targetStaff.getManager().getId();
		model.addAttribute("staffID", staffID);
		model.addAttribute("managerID", managerID);
		model.addAttribute("managerName", managerName);
		
		List<OvertimeApplication> staffOTs= _OTApplicationService.getAllByStaff(_targetStaff);
		model.addAttribute("staffOTApplications", staffOTs);
		return "applyOT";
	}
	
	@GetMapping("/OTApplicationsHistory")
	public String ListOTApplications(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);        
        Long staffID = userSession.getStaffId();
        Staff _targetStaff = _StaffService.FindByUserId(staffID);
        String managerName = _targetStaff.getManager().getName();
        Long managerID = _targetStaff.getManager().getId();
		model.addAttribute("staffID", staffID);
		model.addAttribute("managerID", managerID);
		model.addAttribute("managerName", managerName);
		
		List<OvertimeApplication> staffOTs= _OTApplicationService.getAllByStaff(_targetStaff);
		model.addAttribute("staffOTApplications", staffOTs);
		return "OTClaimHistory";
	}
	
	@GetMapping("/SubmitOTApplication")
	public String submitOT(@RequestParam String staffID, @RequestParam String dateOfOT,
			@RequestParam String numberOTHours, @RequestParam String AdditionalRemarks,
			 @RequestParam String managerID) {
		LocalDateTime OT_dt = StringToDateTimeYYYYMMDD.convertYYYYMMDD_DT(dateOfOT);
		Long _staffID = Long.parseLong(staffID);
        Staff _targetStaff = _StaffService.FindByUserId(_staffID);
		Long _managerID = Long.parseLong(managerID);
		Staff _targetManager = _StaffService.FindByUserId(_managerID);
		Double _numbeHours = Double.parseDouble(numberOTHours);
		OvertimeApplication OT_Application = new OvertimeApplication(_targetStaff, OT_dt, 
				_numbeHours, AdditionalRemarks, _targetManager);
		_OTApplicationService.newApplication(OT_Application);
		return "redirect:/LeaveApplication";
	}
}
