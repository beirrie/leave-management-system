package sg.nus.iss.leavesystem.ca.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.leavesystem.ca.model.*;
import sg.nus.iss.leavesystem.ca.model.dto.CompensationReportDTO;
import sg.nus.iss.leavesystem.ca.model.dto.LeaveApprovalDTO;
import sg.nus.iss.leavesystem.ca.model.dto.LeaveReportDTO;
import sg.nus.iss.leavesystem.ca.model.dto.OvertimeApprovalDTO;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;
import sg.nus.iss.leavesystem.ca.service.*;
import sg.nus.iss.leavesystem.ca.validator.ManagerRejectLeaveValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private LeaveApplicationService leaveAppService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private OvertimeApplicationService overtimeApplicationService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private ManagerRejectLeaveValidator managerRejectLeaveValidator;

    @Autowired
    private CsvExportService csvExportService;
    @Autowired
    private StaffRepository staffRepository;

    @InitBinder("dto")
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(managerRejectLeaveValidator);
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
        String[] approveOrReject = {"Approved", "Rejected"};
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
            String[] approveOrReject = {"Approved", "Rejected"};
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
        String[] approveOrReject = {"Approved", "Rejected"};
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

    @GetMapping("/leave_report")
    public String leaveReportsView(@RequestParam Map<String, String> params,
                                   @ModelAttribute LeaveReportDTO leaveReportDTO, Model model, HttpSession session) {
        Staff manager = getStaff(model, session);
        model.addAttribute("leaves", leaveAppService.getStaffLeavesByManager(manager));

        List<Staff> staffList = staffService.findByManager(manager);
        Map<Long, String> id_Name = new HashMap<>();
        id_Name.put(0L, "All");
        for (var staff : staffList) {
            id_Name.put(staff.getId(), staff.getName());
        }

        List<LeaveType> typeList = leaveTypeService.GetAll();
        List<String> leaveTypeNameList = new ArrayList<>();
        leaveTypeNameList.add("All");
        for (var leaveType : typeList) {
            leaveTypeNameList.add(leaveType.getLeaveTypeName());
        }

        List<LeaveApplication> listLeave = new ArrayList<>();

        if (!params.isEmpty()) {
            Long staffId = Long.valueOf(params.get("staffId"));
            String leaveTypeName = params.get("leaveTypeName");
            String startPeriod = params.get("startPeriod");
            String endPeriod = params.get("endPeriod");

            listLeave.addAll(leaveAppService.getListForReport(manager.getId(), staffId,
                    leaveTypeName, startPeriod, endPeriod));
        } else {
            listLeave.addAll(leaveAppService.getStaffLeavesByManager(manager));
        }


        model.addAttribute("dtoLeave", leaveReportDTO);
        model.addAttribute("leaves", listLeave);
        model.addAttribute("staffName", id_Name);
        model.addAttribute("leaveTypeName", leaveTypeNameList);
        return "leaveReport";
    }

    @GetMapping("leave_report/export")
    public void leaveReportsExport(@ModelAttribute LeaveReportDTO leaveReportDTO,
                                   HttpServletResponse servletResponse, HttpSession session) throws IOException {
        UserSession userSession = (UserSession) session.getAttribute("user");
        Staff manager = staffService.findStaffByID(userSession.getStaffId());

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"leave_report.csv\"");
        csvExportService.leaveReportToCsv(servletResponse.getWriter(), manager, leaveReportDTO.getStaffId(), leaveReportDTO.getLeaveTypeName(),
                leaveReportDTO.getStartPeriod(), leaveReportDTO.getEndPeriod());
    }

    @GetMapping("/compensation_report")
    public String compensationReportsView(@RequestParam Map<String, String> params,
                                          @ModelAttribute CompensationReportDTO compensationReportDTO, Model model,
                                          HttpSession session) {
        Staff manager = getStaff(model, session);
        model.addAttribute("overtimes", overtimeApplicationService.getAllByManager(manager));

        List<Staff> staffList = staffService.findByManager(manager);
        Map<Long, String> id_Name = new HashMap<>();
        id_Name.put(0L, "All");
        for (var staff : staffList) {
            id_Name.put(staff.getId(), staff.getName());
        }

        List<OvertimeApplication> overtimeList = new ArrayList<>();

        if (!params.isEmpty()) {
            Long staffId = Long.parseLong(params.get("staffId"));
            if(staffId!=0L) {
                Staff staff = staffService.findById(staffId);

                overtimeList.addAll(overtimeApplicationService.getAllByStaff(staff));
            } else {
                overtimeList.addAll(overtimeApplicationService.getAllByManager(manager));
            }
            model.addAttribute("overtimes", overtimeList);
        }

        model.addAttribute("dtoComp", compensationReportDTO);
        model.addAttribute("staffName", id_Name);
        return "otReport";
    }

    @GetMapping("compensation_report/export")
    public void compensationReportsExport(@ModelAttribute CompensationReportDTO compensationReportDTO,
                                          HttpServletResponse servletResponse, HttpSession session) throws IOException {
        UserSession userSession = (UserSession) session.getAttribute("user");
        Staff manager = staffService.findStaffByID(userSession.getStaffId());

        servletResponse.setContentType("text/csv");
        servletResponse.addHeader("Content-Disposition", "attachment; filename=\"compensation_report.csv\"");
        csvExportService.compensationReportToCsv(servletResponse.getWriter(), manager,
                compensationReportDTO.getStaffId());
    }

    private Staff getStaff(Model model, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("user");
        List<String> roles = userSession.getUserRoles();
        model.addAttribute("roles", roles);
        return staffService.findStaffByID(userSession.getStaffId());
    }

}
