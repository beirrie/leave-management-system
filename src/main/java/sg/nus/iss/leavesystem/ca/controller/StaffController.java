package sg.nus.iss.leavesystem.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.service.UserService;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@Autowired
	UserService userService;

	@Autowired
	LeaveSchemeService leaveSchemeService;

	@GetMapping("/list")
	public String staffListPage(Model model) {
		model.addAttribute("staffList", staffService.getStaffList());
		return "staff-list";
	}

	@GetMapping("/create")
	public String newStaffPage(Model model) {
		model.addAttribute("userStaffForm", new UserStaffForm());
		model.addAttribute("leaveSchemes", leaveSchemeService.getAllLeaveScheme());
		model.addAttribute("managers", staffService.findAllManagers());
		return "staff-new";
	}

	@PostMapping("/create")
	public String newStaffPage(@ModelAttribute UserStaffForm staff, BindingResult result) {
		Staff newStaff = new Staff();
		newStaff.setUser(userService.findUser(staff.getUserId()));
		newStaff.setFirstName(staff.getFirstName());
		newStaff.setLastName(staff.getLastName());
		newStaff.setEmailAdd(staff.getEmailAdd());
		newStaff.setManager(staffService.findStaffByID(staff.getManagerId()));
		newStaff.setLeaveScheme(leaveSchemeService.getLeaveSchemeByID(Long.parseLong(staff.getLeaveSchemeId())));
		staffService.createStaff(newStaff);
		return "redirect:/admin/staff/list";
	}

	@GetMapping("/deactivate/{id}")
	public String deleteStaff(@PathVariable("id") String id) {
		Staff staff = staffService.findStaffByID(id);
		staffService.deactivateStaff(staff);
		User user = userService.findUserByStaffID(id);
		userService.deactivateUser(user);

		String message = "The staff " + staff.getId() + " was successfully deactivated.";
		System.out.println(message);

		return "redirect:/admin/staff/list";
	}
}
