package sg.nus.iss.leavesystem.ca.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.model.dto.LeaveApprovalDTO;
import sg.nus.iss.leavesystem.ca.model.dto.OvertimeApprovalDTO;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.validator.ManagerRejectLeaveValidator;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    //private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    private LeaveApplicationService leaveAppService;

    @Autowired
    private OvertimeApplicationService overtimeApplicationService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ManagerRejectLeaveValidator managerRejectLeaveValidator;

    @InitBinder("dto")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(managerRejectLeaveValidator);
    }

    @GetMapping("/home")
    public String managerHomePg(Model model) {
        return "managerHome";
    }

    @GetMapping("/pending_leave_applications")
    public String ViewPendingLeavesApp(HttpSession session, Model model) {
        Staff manager1 = getStaff(model, session);

        model.addAttribute("leaves", leaveAppService.getAllPendingByManager(manager1));
        return "managerPendingLeavesApps";
    }

    @GetMapping("/pending_ot_applications")
    public String ViewPendingOTApp(HttpSession session, Model model) {
        Staff manager1 = getStaff(model, session);

        model.addAttribute("overtimes",
                overtimeApplicationService.getAllPendingByManager(manager1));
        return "managerPendingOTApps";
    }

    @GetMapping("/leave_application/{id}")
    public String showLeaveAppById(@PathVariable("id") Long leaveId, Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();

        Staff manager = staffService.findStaffByID(userSession.getStaffId());
        
        model.addAttribute("roles", roles); 
        model.addAttribute("roles", roles);
        String[] approveOrReject = { "Approved", "Rejected" };
        LeaveApplication leaveAppToApproveOrReject = leaveAppService.getLeaveById(leaveId);
        
        List<LeaveApplication> overlapLeaveApps = leaveAppService.getOverlapLeavesWithCurrentStaff(leaveAppToApproveOrReject, manager);
        model.addAttribute("overlapLeaves", overlapLeaveApps);
        model.addAttribute("leave", leaveAppToApproveOrReject);
        LeaveApprovalDTO dto = new LeaveApprovalDTO();
        dto.setLeaveId(leaveId);
        model.addAttribute("dto", dto);
        model.addAttribute("ListApproveOrReject", approveOrReject);
        return "managerApproveOrRejectLeave";
    }

    @PostMapping("/modify_leave")
    public String approveOrRejectLeaveAppById(@Valid @ModelAttribute("dto") LeaveApprovalDTO leaveApprovalDTO,
            BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            UserSession userSession = (UserSession) session.getAttribute("user");
            List<String> roles = userSession.getUserRoles();

            model.addAttribute("roles", roles);
            String[] approveOrReject = { "Approved", "Rejected" };
            model.addAttribute("leave", leaveAppService.getLeaveById(leaveApprovalDTO.getLeaveId()));
            model.addAttribute("ListApproveOrReject", approveOrReject);
            return "managerApproveOrRejectLeave";
        }
        UserSession userSession = (UserSession) session.getAttribute("user");
        Staff approver = staffService.findStaffByID(userSession.getStaffId());

        LeaveApplication retrievedApp = leaveAppService.getLeaveById(leaveApprovalDTO.getLeaveId());
        leaveAppService.setApprovalStatus(retrievedApp, leaveApprovalDTO.getApplicationStatus(),
                leaveApprovalDTO.getApproverRemark(),
                approver);
        return "redirect:/manager/pending_leave_applications";
    }

    @GetMapping("/ot_application/{id}")
    public String showOTAppById(@PathVariable("id") Long otId, Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();

        model.addAttribute("roles", roles);
        String[] approveOrReject = { "Approved", "Rejected" };
        model.addAttribute("overtime", overtimeApplicationService.getById(otId));
        OvertimeApprovalDTO dtoOT = new OvertimeApprovalDTO();
        dtoOT.setOtId(otId);
        model.addAttribute("dtoOT", dtoOT);
        model.addAttribute("ListApproveOrReject", approveOrReject);
        return "managerApproveOrRejectOvertime";
    }

    @PostMapping("/modify_overtime")
    public String approveOrRejectOTAppById(@ModelAttribute OvertimeApprovalDTO overtimeApprovalDTO,
            BindingResult bindingResult, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        Staff approver = staffService.findStaffByID(userSession.getStaffId());

        OvertimeApplication retrievedApp = overtimeApplicationService.getById(overtimeApprovalDTO.getOtId());
        overtimeApplicationService.setApprovalStatus(retrievedApp, overtimeApprovalDTO.getApplicationStatus(),
                overtimeApprovalDTO.getApproverRemark(),
                approver);
        return "redirect:/manager/pending_ot_applications";
    }

    @GetMapping("/employees_leave_history")
    public String getAllStaffLeaveHistory(Model model, HttpSession session) {
        Staff manager = getStaff(model, session);
        model.addAttribute("leaves", leaveAppService.getStaffLeavesByManager(manager));

        return "managerAllStaffLeaveHistory";
    }

    @GetMapping("/employees_ot_history")
    public String getAllStaffOTHistory(Model model, HttpSession session) {
        Staff manager = getStaff(model, session);
        model.addAttribute("overtimes", overtimeApplicationService.getAllByManager(manager));

        return "managerAllStaffOvertimeHistory";
    }

    private Staff getStaff(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);
        Staff manager = staffService.findStaffByID(userSession.getStaffId());
        return manager;
    }

}
