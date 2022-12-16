package sg.nus.iss.leavesystem.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.dto.StaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@Autowired
	LeaveSchemeService leaveSchemeService;

	@GetMapping("/list")
	public String staffListPage(Model model) {
		model.addAttribute("staffList", staffService.getStaffList());
		return "staff-list";
	}

	@GetMapping("/create")
	public String newStaffPage(Model model) {
		model.addAttribute("staffForm", new StaffForm());
		model.addAttribute("leaveSchemes", leaveSchemeService.getAllLeaveScheme());
		model.addAttribute("managers", staffService.findAllManagers());
		return "staff-new";
	}

	@PostMapping("/create")
	public String newStaffPage(@ModelAttribute StaffForm staffForm, BindingResult result) {
		Staff newStaff = new Staff();
		newStaff.setFirstName(staffForm.getFirstName());
		newStaff.setLastName(staffForm.getLastName());
		newStaff.setEmailAdd(staffForm.getEmailAdd());
		newStaff.setManager(staffService.findStaffByID(staffForm.getManagerId()));
		newStaff.setLeaveScheme(leaveSchemeService.getLeaveSchemeByID(Long.parseLong(staffForm.getLeaveSchemeId())));
		staffService.createStaff(newStaff);
		return "redirect:/admin/staff/list";
	}
}
