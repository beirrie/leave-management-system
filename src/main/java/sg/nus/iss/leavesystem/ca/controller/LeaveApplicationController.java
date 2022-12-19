package sg.nus.iss.leavesystem.ca.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.LeaveApplicationForm;
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.UserSession;
import sg.nus.iss.leavesystem.ca.service.LeaveApplicationService;
import sg.nus.iss.leavesystem.ca.service.LeaveTypeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.util.Util;

@Controller
public class LeaveApplicationController {

    @Autowired
    StaffService staffService;
    @Autowired
    LeaveTypeService leaveTypeService;
    @Autowired
    LeaveApplicationService leaveApplicationService;

    @GetMapping(value={"/LeaveApplication", "/"})
    public String Main(HttpSession session, Model model) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);
        for(String role: roles) {
        	System.out.println(role);
        }
//		model.addAttribute("userSession", userSession);
//        if (userSession == null)
//            return "redirect:/login";
        return "LeaveApplication";
    }

    @GetMapping("/LeaveBalance")
    public String LeaveBalance(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);
//        if (userSession == null)
//            return "redirect:/login";

        Staff staff = staffService.FindByUserId(userSession.getStaffId());

        model.addAttribute("staff", staff);

        return "LeaveBalance";
    }

    @GetMapping("/LeaveHistory")
    public String LeaveHistory(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
//        if (userSession == null)
//            return "redirect:/login";
        List<LeaveApplication> leaveApplications = this.leaveApplicationService.GetByStaffId(userSession.getStaffId());
        model.addAttribute("leaveList", leaveApplications);
        return "LeaveHistory";
    }

    @GetMapping("/AddLeave")
    public String AddLeave(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        LeaveApplicationForm leaveApplication = new LeaveApplicationForm();
        model.addAttribute("leaveForm", leaveApplication);
        model.addAttribute("leaveTypeList", leaveTypeService.GetAll());
        model.addAttribute("coveringStaffList", staffService.findAllStaff());
        return "AddLeave";
    }

    @GetMapping("/editLeave/{id}")
    public String EditLeave(Model model, HttpSession session, @PathVariable(value = "id") long id) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles); 
        LeaveApplication leaveApplication = this.leaveApplicationService.GetById(id).get();

        LeaveApplicationForm leaveApplicationForm = new LeaveApplicationForm();
        leaveApplicationForm.setId(leaveApplication.getId());
        leaveApplicationForm.setLeaveType(leaveApplication.getTypeOfLeave());
        leaveApplicationForm.setAdditionalComments(leaveApplication.getAdditionalComments());
        leaveApplicationForm.setCoveringStaff(leaveApplication.getCoveringStaff());
        leaveApplicationForm.setContactNumber(leaveApplication.getContactNumber());
        leaveApplicationForm.setApplicationStatus(leaveApplication.getApplicationStatus());
        leaveApplicationForm.setIsAbroad(leaveApplication.getIsAbroad());
        leaveApplicationForm.setStartDate(leaveApplication.getStartDate());
        leaveApplicationForm.setEndDate(leaveApplication.getEndDate());

        model.addAttribute("leaveForm", leaveApplicationForm);
        model.addAttribute("leaveTypeList", leaveTypeService.GetAll());
        model.addAttribute("coveringStaffList", staffService.findAllStaff());
        return "EditLeave";
    }

    @GetMapping("/deleteLeave/{id}")
    public String DeleteLeave(Model model, HttpSession session, @PathVariable(value = "id") long id) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles); 
        LeaveApplication leaveApplication = this.leaveApplicationService.GetById(id).get();

        this.leaveApplicationService.DeleteLeave(leaveApplication);

        return "redirect:/LeaveHistory";
    }

    @PostMapping("/saveLeave")
    public String SaveLeave(@ModelAttribute("leaveForm") LeaveApplicationForm leaveForm, BindingResult result,
            HttpSession session,Model model) {
        UserSession userSession = (UserSession) session.getAttribute("user");
//        if (userSession == null)
//            return "redirect:/login";
            
        var staff = this.staffService.FindByUserId(userSession.getStaffId());
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setTypeOfLeave(leaveForm.getLeaveType());
        leaveApplication.setEmployee(staff);
        leaveApplication.setIsAbroad(leaveForm.getIsAbroad());
        leaveApplication.setContactNumber(leaveForm.getContactNumber());
        leaveApplication.setCoveringStaff(leaveForm.getCoveringStaff());
        leaveApplication.setStartDate(Util.convertStringToDate(leaveForm.getStartDateStr()));
        leaveApplication.setEndDate(Util.convertStringToDate(leaveForm.getEndDateStr()));
        leaveApplication.setAdditionalComments(leaveForm.getAdditionalComments());
        leaveApplication.setApplicationStatus("Applied");
        leaveApplication.setApplicationDate(LocalDateTime.now());
        leaveApplication.setEmployeeManager(staff);
        leaveApplication.setDateReviewed(LocalDateTime.now());
        leaveApplication.setMgrRemarks("");

        if(leaveApplication.getEndDate().isBefore(leaveApplication.getStartDate()))
        {
            model.addAttribute("leaveForm", leaveForm);
            model.addAttribute("leaveTypeList", leaveTypeService.GetAll());
            model.addAttribute("coveringStaffList", staffService.findAllStaff());
            return "AddLeave";
        }
            
        this.leaveApplicationService.CreateApplication(leaveApplication);
        return "redirect:/LeaveApplication";
    }

    @PostMapping("/updateLeave")
    public String UpdateLeave(@ModelAttribute("leaveForm") LeaveApplicationForm leaveForm, BindingResult result,
            HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
//        if (userSession == null)
//            return "redirect:/login";
        var staff = this.staffService.FindByUserId(userSession.getStaffId());
        LeaveApplication leaveApplication = new LeaveApplication();
        leaveApplication.setId(leaveForm.getId());
        leaveApplication.setTypeOfLeave(leaveForm.getLeaveType());
        leaveApplication.setEmployee(staff);
        leaveApplication.setIsAbroad(leaveForm.getIsAbroad());
        leaveApplication.setContactNumber(leaveForm.getContactNumber());
        leaveApplication.setCoveringStaff(leaveForm.getCoveringStaff());
        leaveApplication.setStartDate(Util.convertStringToDate(leaveForm.getStartDateStr()));
        leaveApplication.setEndDate(Util.convertStringToDate(leaveForm.getEndDateStr()));
        leaveApplication.setAdditionalComments(leaveForm.getAdditionalComments());
        leaveApplication.setApplicationStatus("Updated");
        leaveApplication.setApplicationDate(LocalDateTime.now());
        leaveApplication.setEmployeeManager(staff);
        leaveApplication.setDateReviewed(LocalDateTime.now());
        leaveApplication.setMgrRemarks("");

        this.leaveApplicationService.UpdateApplication(leaveApplication);
        return "redirect:/LeaveApplication";
    }
}
