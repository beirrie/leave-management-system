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
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.dto.LeaveApprovalDTO;
import sg.nus.iss.leavesystem.ca.model.dto.OvertimeApprovalDTO;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;


import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private LeaveApplicationService leaveAppService;
    @Autowired
    private OvertimeApplicationService overtimeApplicationService;
    @Autowired
    private StaffService staffService;


    @GetMapping("/")
    public String managerHomePg(Model model) {
        return "managerHome";
    }

    @GetMapping("/pending_leave_applications")
    public String ViewPendingLeavesApp(HttpSession session, Model model) {
        model.addAttribute("pendingAllLeave", leaveAppService.getAllLeaveApplications());

        return "managerPendingLeavesApps";
    }

    @GetMapping("/pending_ot_applications")
    public String ViewPendingOTApp(HttpSession session, Model model) {
        model.addAttribute("pendingAllOt", overtimeApplicationService.getAllOvertimeApplication());

        return "managerPendingOTApps";
    }

    @GetMapping("/leave_application/{id}")
    public String showLeaveAppById(@PathVariable("id") Long leaveId, Model model) {
        model.addAttribute("leaveApp", leaveAppService.getLeaveById(leaveId));

        return "managerViewLeaveAppByStaffId";
    }

    @PostMapping("/leave_application/{id}")
    public String approveOrRejectLeaveAppById(@ModelAttribute LeaveApprovalDTO leaveApprovalDTO,
                                              BindingResult bindingResult, @PathVariable("id") Long leaveId) {
        LeaveApplication retrievedApp = leaveAppService.getLeaveById(leaveId);
        Staff approver = staffService.findStaffByID(leaveApprovalDTO.getApproverId().toString());
        leaveAppService.setApprovalStatus(retrievedApp, "Approved", leaveApprovalDTO.getApproverRemark(),
                approver);

        return "redirect:/manager/home";
    }

    @GetMapping("/ot_application/{id}")
    public String showOTAppById(@PathVariable("id") Long overtimeId, Model model) {
        model.addAttribute("overtimeApp", overtimeApplicationService.getById(overtimeId));

        return "managerViewOTAppByStaffId";
    }

    @PostMapping("/ot_application/{id}")
    public String approveOrRejectOTAppById(@ModelAttribute OvertimeApprovalDTO overtimeApprovalDTO, BindingResult bindingResult,
                                           @PathVariable("id") Long overtimeId) {
        OvertimeApplication retrievedApp = overtimeApplicationService.getById(overtimeId);
        Staff approver = staffService.findStaffByID(overtimeApprovalDTO.getApproverId().toString());
        overtimeApplicationService.setApprovalStatus(retrievedApp, "Approved", overtimeApprovalDTO.getApproverRemark(),
                approver);

        return "redirect:/manager/home";
    }

    @GetMapping("/employees_leave_history")
    public String getAllStaffLeaveHistory(HttpSession session, Model model) {
        //model.addAttribute("leaveList", leaveAppService.getStaffLeavesById(Long.valueOf(sessionid);

        return "managerAllStaffLeaveHistory";
    }

}
