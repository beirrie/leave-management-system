package sg.nus.iss.leavesystem.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.LeaveTypeService;
import sg.nus.iss.leavesystem.ca.service.PublicHolidayService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	public LeaveSchemeService _leaveSchemeService;
//	
//	@Autowired
//	public LeaveTypeService _leaveTypeService;
//	
//	@Autowired
//	public PublicHolidayService _publicHolidayService;
	
	@GetMapping("/viewleaveschemes")
	public String viewLeaveSchemes(Model model) {
		return "viewleaveschemes";
	}
	
	@GetMapping("/addleavescheme")
	public String editLeaveScheme(Model model) {
		LeaveScheme emptyLeaveScheme = new LeaveScheme();
		model.addAttribute("leavescheme", emptyLeaveScheme);
		return "addleavescheme";
	}
	
	@PostMapping("/addnewleavescheme")
	public String submitNewLeaveScheme(LeaveScheme newLeaveScheme) {
		System.out.println(newLeaveScheme.getEmploymentScheme());
		System.out.println(newLeaveScheme.getAnnualLeaveEntitlement());
		System.out.println(newLeaveScheme.getMedicalLeaveEntitlement());
		_leaveSchemeService.createLeaveScheme(newLeaveScheme);
		return "redirect:/admin/viewleaveschemes";
	}
}
