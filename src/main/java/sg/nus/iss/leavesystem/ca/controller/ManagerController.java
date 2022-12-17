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

import java.time.LocalDateTime;
import java.util.List;
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
    	
    	List<LeaveApplication> pendingAllLeave =  leaveAppService.getStaffLeavesByManagerAndStatus(staffService.findAllManagers().get(0), "Applied Updated");
        model.addAttribute("pendingAllLeave",pendingAllLeave);
        return "managerPendingLeavesApps";
    }

    @GetMapping("/pending_ot_applications")
    public String ViewPendingOTApp(HttpSession session, Model model) {
        model.addAttribute("pendingAllOt", overtimeApplicationService.getAllOvertimeApplication());
        
        return "managerPendingOTApps";
    }

    @GetMapping("/leave_application/{id}")
    public String showIndividualLeaveApp(@PathVariable("id") Long leaveId, Model model) {
    	Staff manager = staffService.findAllManagers().get(0);
    	if(manager != null) {
    		LeaveApprovalDTO leaveApprovalDTO = new LeaveApprovalDTO("", manager.getId(), "",leaveId);
    		model.addAttribute("leaveApprovalDTO", leaveApprovalDTO);
    		model.addAttribute("ListApproveOrReject", List.of("Approved","Rejected"));
    	}
        return "manager-approve-or-reject-leave";
    }
    
    @PostMapping("/leave_application/update_status")
    public String approveOrRejectLeaveAppById(@ModelAttribute LeaveApprovalDTO leaveApprovalDTO) {
    	
    	LeaveApplication retrievedApp = leaveAppService.getLeaveById(leaveApprovalDTO.getLeaveId());
        Staff approver = staffService.findStaffByID(leaveApprovalDTO.getApproverId().toString());
        leaveAppService.setApprovalStatus(retrievedApp, leaveApprovalDTO.getApplicationStatus(), leaveApprovalDTO.getApproverRemark(),
                approver);
        //testing
//        LeaveApplication leaveApplication = leaveAppService.getLeaveById(leaveApprovalDTO.getLeaveId());
//        System.out.println(leaveApplication.getApplicationStatus());
//        System.out.println(leaveApplication.getMgrRemarks());
        return "redirect:/manager/";
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

        return "redirect:/manager";
    }

    @GetMapping("/employees_leave_history")
    public String getAllStaffLeaveHistory(HttpSession session, Model model) {
        //model.addAttribute("leaveList", leaveAppService.getStaffLeavesById(Long.valueOf(sessionid);
    	
        return "managerAllStaffLeaveHistory";
    }

}
