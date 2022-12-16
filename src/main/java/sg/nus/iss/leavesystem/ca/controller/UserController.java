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

import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.service.RoleService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private RoleService roleService;

	@GetMapping("/list")
	public String userListPage(Model model) {
		List<User> userList = userService.findAllUsers();
		model.addAttribute("userList", userList);
		return "user-list";
	}

	@GetMapping("/create")
	public String newStaffPage(Model model) {
		model.addAttribute("user", new User());
		List<Role> roles = roleService.findAllRoles();
		model.addAttribute("roles", roles);
		model.addAttribute("staffList", staffService.findAllStaff());
		return "user-new";
	}

	@PostMapping("/create")
	public String createNewUser(@ModelAttribute User user, BindingResult result, Model model) {
		userService.createUser(user);
		return "redirect:/admin/user/list";
	}
}
