package sg.nus.iss.leavesystem.ca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.servlet.http.HttpSession;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.dto.LeaveApprovalDTO;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private LeaveApplicationService leaveAppService;
    private OvertimeApplicationService overtimeApplicationService;
    private StaffService staffService;

    @GetMapping("/home")
    public String managerHomePg(Model model) {
        return "managerHome";
    }

    @GetMapping("/pending_leave_applications")
    public String ViewPendingLeavesApp(HttpSession session, Model model) {

        model.addAttribute("leaves", leaveAppService.getAllLeaveApplications());
        return "managerPendingLeavesApps";
    }

    @GetMapping("/pending_ot_applications")
    public String ViewPendingOTApp(HttpSession session, Model model) {

        model.addAttribute("ot", overtimeApplicationService.getAllOvertimeApplication());
        return "managerPendingOTApps";
    }

    @GetMapping("/leave_application/{id}")
    public String showLeaveAppById(@PathVariable("id") Long id, Model model) {
        String[] approveOrReject = { "Approved", "Rejected" };
        model.addAttribute("leaveApprovalDTO", new LeaveApprovalDTO());
        model.addAttribute("leave", leaveAppService.getLeaveById(id));
        model.addAttribute("approverId", 1);
        model.addAttribute("leaveId", id);
        model.addAttribute("ListApproveOrReject", approveOrReject);
        return "managerApproveOrRejectLeave";
    }

    @PostMapping("/leave_application")
    public String approveOrRejectLeaveAppById(@ModelAttribute LeaveApplication leaveApp, BindingResult bindingResult) {
        return "redirect:/manager/home";
    }

    @GetMapping("/ot_application/{id}")
    public String showOTAppById(@PathVariable("id") Long staffId) {
        return "managerViewOTAppByStaffId";
    }

    @PostMapping("/ot_application/{id}")
    public String approveOrRejectOTAppById(@ModelAttribute OvertimeApplication otApp, BindingResult bindingResult,
            @PathVariable Long staffId) {
        return "redirect:/manager/home";
    }

    @GetMapping("/employees_leave_history")
    public String getAllStaffLeaveHistory(Model model) {
        model.addAttribute("leaves", leaveAppService.getAllLeaveApplications());

        return "managerAllStaffLeaveHistory";
    }

}
