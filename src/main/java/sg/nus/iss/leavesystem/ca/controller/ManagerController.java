package sg.nus.iss.leavesystem.ca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private LeaveApplicationService leaveAppService;

    @GetMapping("/home")
    public String managerHomePg(Model model) {
        return "managerHome";
    }

    @GetMapping("/pendingLeaveApplications")
    public String ViewPendingLeavesApp(HttpSession session,Model model) {

        return "managerPendingLeavesApps";
    }

    @GetMapping("/pendingOTApplications")
    public String ViewPendingOTApp(HttpSession session,Model model) {

        return "managerPendingOTApps";
    }

    @GetMapping("/leaveapplication/{id}")
    public String showLeaveAppById(@PathVariable("id") Long staffId) {

        return "managerViewLeaveAppByStaffId";
    }

    @PostMapping("/leaveapplication/{id}")
    public String approveOrRejectLeaveAppById(@ModelAttribute LeaveApplication leaveApp,BindingResult bindingResult,@PathVariable Long staffId) {

        return "redirect:/manager/home";
    }
    @GetMapping("/OTapplication/{id}")
    public String showOTAppById(@PathVariable("id") Long staffId) {

        return "managerViewOTAppByStaffId";
    }

    @PostMapping("/OTapplication/{id}")
    public String approveOrRejectOTAppById(@ModelAttribute OvertimeApplication otApp,BindingResult bindingResult,@PathVariable Long staffId) {

        return "redirect:/manager/home";
    }

    @GetMapping("/employeesLeaveHistory")
    public String getAllStaffLeaveHistory() {

        return "managerAllStaffLeaveHistory";
    }
}
