package sg.nus.iss.leavesystem.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.service.UserService;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    public HomeController() {
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping(value = "/authenticate")
    public String authenticate(Model model, @ModelAttribute("user") User user, BindingResult bindingResult,HttpSession session) {
        System.out.println("Attempting to authenticate");
    	User dbUser = userService.authenticate(user.getUserName(), user.getPassword());
        if (dbUser == null) {
            model.addAttribute("loginMessage", "Incorrect username/password");
            return "login";
        }
        UserSession userSession = new UserSession();
        userSession.setStaffId(dbUser.getEmployee().getId());
        userSession.setUserName(dbUser.getUserName());
        userSession.setUserRoles(dbUser.getRoleSet());
        session.setAttribute("user", userSession);
        return "redirect:/LeaveApplication";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
        return "login";
    }
}
