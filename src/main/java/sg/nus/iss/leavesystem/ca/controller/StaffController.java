package sg.nus.iss.leavesystem.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.RoleService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.service.UserService;
import sg.nus.iss.leavesystem.ca.validator.UserStaffFormValidator;

@Controller
@RequestMapping("/admin/staff")
public class StaffController {
	@Autowired
	private UserStaffFormValidator userStaffFormValidator;

	@InitBinder("userStaffForm")
	private void initUserStaffFormBinder(WebDataBinder binder) {
		binder.addValidators(userStaffFormValidator);
	}

	@Autowired
	StaffService staffService;

	@Autowired
	UserService userService;

	@Autowired
	LeaveSchemeService leaveSchemeService;

	@Autowired
	RoleService roleService;

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
	public String newStaffPage(@Valid @ModelAttribute("userStaffForm") UserStaffForm staff, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			User userDetails = new User();
			userDetails.setId(staff.getUserId());
			userDetails.setPassword(staff.getPassword());
			userDetails.setUserName(staff.getUserName());
			model.addAttribute("user", userDetails);
			model.addAttribute("leaveSchemes", leaveSchemeService.getAllLeaveScheme());
			model.addAttribute("managers", staffService.findAllManagers());
			return "staff-new";
		}
		Staff newStaff = new Staff();
		newStaff.setUser(userService.findUser(staff.getUserId()));
		newStaff.setFirstName(staff.getFirstName());
		newStaff.setLastName(staff.getLastName());
		newStaff.setEmailAdd(staff.getEmailAdd());
		newStaff.setManager(staffService.findStaffByID(staff.getManagerId()));
		newStaff.setLeaveScheme(leaveSchemeService.getLeaveSchemeByID(Long.parseLong(staff.getLeaveSchemeId())));
		newStaff.setAnnualLeaveBalance(staff.getAnnualLeaveBalance());
		newStaff.setMedicalLeaveBalance(staff.getMedicalLeaveBalance());
		staffService.createStaff(newStaff);
		return "redirect:/admin/staff/list";
	}

	@GetMapping("/edit/{id}")
	public String editStaffPage(@PathVariable("id") String id, Model model) {
		Staff staff = staffService.findStaffByID(id);
		User staffUser = userService.findUserByStaffID(id);

		UserStaffForm userStaffForm = new UserStaffForm();
		userStaffForm.setUserId(staffUser.getId());
		userStaffForm.setUserName(staffUser.getUserName());
		userStaffForm.setPassword(staffUser.getPassword());
		userStaffForm.setRoles(staffUser.getRoleSet());

		userStaffForm.setStaffId(id);
		userStaffForm.setFirstName(staff.getFirstName());
		userStaffForm.setLastName(staff.getLastName());
		userStaffForm.setEmailAdd(staff.getEmailAdd());
		userStaffForm.setAnnualLeaveBalance(staff.getAnnualLeaveBalance());
		userStaffForm.setMedicalLeaveBalance(staff.getMedicalLeaveBalance());
		if (staff.getManager() != null) {
			userStaffForm.setManagerId(staff.getManager().getId().toString());
		}

		List<Staff> managers = staffService.findAllManagers();
		if (staff.getSubordinates() != null) {
			managers.remove(staff);
		}

		model.addAttribute("userStaffForm", userStaffForm);
		model.addAttribute("leaveSchemes", leaveSchemeService.getAllLeaveScheme());
		model.addAttribute("managers", managers);
		model.addAttribute("allRoles", roleService.findAllRoles());
		return "staff-edit";
	}

	@PostMapping("/edit/{id}")
	public String editStaff(@Valid @ModelAttribute UserStaffForm userStaffForm, BindingResult result,
			@PathVariable String id, Model model) {

		if (result.hasErrors()) {
			Staff staff = staffService.findStaffByID(id);

			List<Staff> managers = staffService.findAllManagers();
			if (staff.getSubordinates() != null) {
				managers.remove(staff);
			}
			model.addAttribute("leaveSchemes", leaveSchemeService.getAllLeaveScheme());
			model.addAttribute("managers", managers);
			model.addAttribute("allRoles", roleService.findAllRoles());
			return "staff-edit";
		}
		staffService.editStaff(id, userStaffForm);
		String message = "Staff was successfully updated.";
		System.out.println(message);
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
