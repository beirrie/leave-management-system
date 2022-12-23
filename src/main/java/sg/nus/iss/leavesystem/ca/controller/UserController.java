package sg.nus.iss.leavesystem.ca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.service.RoleService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.service.UserService;
import sg.nus.iss.leavesystem.ca.validator.UserStaffFormValidator;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private UserStaffFormValidator userStaffFormValidator;

	@InitBinder("userForm")
	private void initUserStaffFormBinder(WebDataBinder binder) {
		binder.addValidators(userStaffFormValidator);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/list")
	public String userListPage(Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		List<String> roles = userSession.getUserRoles();
		model.addAttribute("roles", roles);
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		return "user-list";
	}

	@GetMapping("/create")
	public String newStaffPage(Model model, HttpSession session) {
		UserSession userSession = (UserSession) session.getAttribute("user");
		List<String> roles = userSession.getUserRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("userForm", new UserStaffForm());
		List<Role> allroles = roleService.findAllRoles();
		model.addAttribute("allroles", allroles);
		model.addAttribute("staffList", staffService.findAllStaff());
		return "user-new";
	}

	@PostMapping("/create")
	public String createNewUser(@Valid @ModelAttribute("userForm") UserStaffForm userForm, BindingResult result,
			RedirectAttributes redirectAttrs, Model model, HttpSession session) {
		if (result.hasErrors()) {
			UserSession userSession = (UserSession) session.getAttribute("user");
			List<String> roles = userSession.getUserRoles();
			model.addAttribute("roles", roles);
			model.addAttribute("userForm", userForm);
			List<Role> allroles = roleService.findAllRoles();
			model.addAttribute("allroles", allroles);
			model.addAttribute("staffList", staffService.findAllStaff());
			return "user-new";
		}
		User newUser = new User();
		newUser.setUserName(userForm.getUserName());
		newUser.setPassword(userForm.getPassword());

		List<Role> newRoleSet = new ArrayList<Role>();
		userForm.getRoles().forEach(role -> {
			Role completeRole = roleService.findRoleByID(role.getId());
			newRoleSet.add(completeRole);
		});
		newUser.setRoleSet(newRoleSet);

		userService.createUser(newUser);
		redirectAttrs.addFlashAttribute("user", newUser);
		return "redirect:/admin/staff/create";
	}
}
