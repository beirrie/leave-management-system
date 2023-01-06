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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.RoleService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.service.UserService;
import sg.nus.iss.leavesystem.ca.validator.UserStaffFormValidator;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
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
	public String staffListPage(Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		List<String> roles = userSession.getUserRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("staffList", staffService.getStaffList());
		return "staff-list";
	}

	@GetMapping("/create")
	public String newStaffPage(Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		User user = (User) model.getAttribute("user");
		if (user != null) {
			List<String> roles = userSession.getUserRoles();
			model.addAttribute("roles", roles);
			UserStaffForm userStaffForm = new UserStaffForm();
			userStaffForm.setUserId(user.getId());
			model.addAttribute("userStaffForm", new UserStaffForm());
			List<LeaveScheme> activeLeaveSchemes = leaveSchemeService.getAllLeaveScheme().stream()
					.filter(x -> x.getIsActive() == true).toList();
			model.addAttribute("leaveSchemes", activeLeaveSchemes);
			model.addAttribute("managers", staffService.findAllManagers());
			return "staff-new";
		}
		return "redirect:/admin/user/create";
	}

	@PostMapping("/create")
	public String newStaffPage(@Valid @ModelAttribute("userStaffForm") UserStaffForm createStaffForm,
			BindingResult result,
			Model model) {
		if (createStaffForm.getUserId() != null) {
			User user = userService.findUser(createStaffForm.getUserId());
			if (result.hasErrors()) {
				user.setUserName(createStaffForm.getUserName());
				model.addAttribute("user", user);
				List<LeaveScheme> activeLeaveSchemes = leaveSchemeService.getAllLeaveScheme().stream()
						.filter(x -> x.getIsActive() == true).toList();
				model.addAttribute("leaveSchemes", activeLeaveSchemes);
				model.addAttribute("managers", staffService.findAllManagers());
				return "staff-new";
			}
			Staff newStaff = new Staff();
			newStaff.setUser(user);
			newStaff.setFirstName(createStaffForm.getFirstName());
			newStaff.setLastName(createStaffForm.getLastName());
			newStaff.setEmailAdd(createStaffForm.getEmailAdd());
			newStaff.setManager(staffService.findStaffByID(createStaffForm.getManagerId()));
			newStaff.setLeaveScheme(
					leaveSchemeService.getLeaveSchemeByID(Long.parseLong(createStaffForm.getLeaveSchemeId())));
			newStaff.setAnnualLeaveBalance(createStaffForm.getAnnualLeaveBalance());
			newStaff.setMedicalLeaveBalance(createStaffForm.getMedicalLeaveBalance());
			staffService.createStaff(newStaff);
			return "redirect:/admin/staff/list";
		}
		return "redirect:/admin/user-new";
	}

	@GetMapping("/edit/{id}")
	public String editStaffPage(@PathVariable("id") String id, Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		List<String> roles = userSession.getUserRoles();
		model.addAttribute("roles", roles);
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
			@PathVariable String id, Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		List<String> roles = userSession.getUserRoles();
		model.addAttribute("roles", roles);
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
	public String deactivateStaff(@PathVariable("id") String id) {
		Staff staff = staffService.findStaffByID(id);
		staffService.deactivateStaff(staff);
		User user = userService.findUserByStaffID(id);
		userService.deactivateUser(user);

		String message = "The staff " + staff.getId() + " was successfully deactivated.";
		System.out.println(message);

		return "redirect:/admin/staff/list";
	}

	@GetMapping("/activate/{id}")
	public String activateStaff(@PathVariable("id") String id) {
		Staff staff = staffService.findStaffByID(id);
		staffService.activateStaff(staff);
		User user = userService.findUserByStaffID(id);
		userService.activateUser(user);

		String message = "The staff " + staff.getId() + " was successfully activated.";
		System.out.println(message);

		return "redirect:/admin/staff/list";
	}
}
