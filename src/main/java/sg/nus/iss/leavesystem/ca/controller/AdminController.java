package sg.nus.iss.leavesystem.ca.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.PublicHoliday;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.LeaveTypeService;
import sg.nus.iss.leavesystem.ca.service.PublicHolidayService;
import sg.nus.iss.leavesystem.ca.util.DateTimeToString;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	public LeaveSchemeService _leaveSchemeService;
//	
//	@Autowired
//	public LeaveTypeService _leaveTypeService;
	
	@Autowired
	public PublicHolidayService _publicHolidayService;
	
	//Methods for Leave Schemes Related Features
	
	@GetMapping("/viewleaveschemes")
	public String viewLeaveSchemes(Model model) {
		List<LeaveScheme> leaveSchemes = _leaveSchemeService.getAllLeaveScheme();
		model.addAttribute("existingschemes", leaveSchemes);
		return "viewleaveschemes";
	}
	
	@GetMapping("/addleavescheme")
	public String editLeaveScheme(Model model) {
		List<LeaveScheme> leaveSchemes = _leaveSchemeService.getAllLeaveScheme();
		LeaveScheme emptyLeaveScheme = new LeaveScheme();
		model.addAttribute("existingschemes", leaveSchemes);
		model.addAttribute("leavescheme", emptyLeaveScheme);
		return "addleavescheme";
	}
	
	@PostMapping("/addnewleavescheme")
	public String submitNewLeaveScheme(LeaveScheme newLeaveScheme) {
		_leaveSchemeService.createLeaveScheme(newLeaveScheme);
		return "redirect:/admin/viewleaveschemes";
	}
	
	@GetMapping("/editleavescheme")
	public String editLeaveScheme(String idLeaveScheme, Model model) {
		Long lsID = Long.parseLong(idLeaveScheme);
		LeaveScheme ls_Edit = _leaveSchemeService.getLeaveSchemeByID(lsID);
		model.addAttribute("leavescheme",ls_Edit);
		return "editleavescheme";
	}
	
	@PostMapping("/updateleavescheme")
	public String updateExistingLeaveScheme(String idLeaveScheme, String employmentScheme, String annualLeaveEntitlement, String medicalLeaveEntitlement) {
		_leaveSchemeService.updateLeaveScheme(idLeaveScheme, employmentScheme, annualLeaveEntitlement, medicalLeaveEntitlement);
		return "redirect:/admin/viewleaveschemes";
	}
	
	@GetMapping("/deactivateleavescheme")
	public String updateExistingLeaveScheme(String idLeaveScheme) {
		_leaveSchemeService.deactivateLeaveScheme(idLeaveScheme);
		return "redirect:/admin/viewleaveschemes";
	}
	
	//Methods for Public Holidays Related Features
	
	@GetMapping("/viewpublicholidays")
	public String viewpublicholidays(Model model) throws JsonMappingException, JsonProcessingException{
		List<PublicHoliday> publicHolidays = _publicHolidayService.getAllPublicHolidays();
		int counter = publicHolidays.size();
		model.addAttribute("holidays", publicHolidays);
		model.addAttribute("counter", counter);
		return "viewpublicholidays";
	}
	
	@PostMapping("/pullpublicholiday")
	public String pullPublicHolidays(Model model) throws JsonMappingException, JsonProcessingException {
		_publicHolidayService.deleteAllPublicHolidaysData();
		_publicHolidayService.pullPublicHolidaysData();
		return "redirect:/admin/viewpublicholidays";
	}
	
	@GetMapping("/addpublicholiday")
	public String addpublicholiday(Model model) {
		List<PublicHoliday> publicHolidays = _publicHolidayService.getAllPublicHolidays();
		PublicHoliday emptyPublicHoliday = new PublicHoliday();
		model.addAttribute("holidays", publicHolidays);
		model.addAttribute("ph", emptyPublicHoliday);
		return "addpublicholiday";
	}
	
	@PostMapping("/addnewpublicholiday")
	public String addnewpublicholiday(String publicHolidayName, 
			String dateOfHoliday, String description) {
		PublicHoliday newHoliday = _publicHolidayService.createPublicHoliday(publicHolidayName, dateOfHoliday, description);
		return "redirect:/admin/viewpublicholidays";
	}
	
	@GetMapping("/editpublicholiday")
	public String addnewpublicholiday(String idHoliday, Model model) {
		Long phID = Long.parseLong(idHoliday);
		PublicHoliday ph_Edit = _publicHolidayService.getPublicHoliday(phID);
		LocalDateTime dt = ph_Edit.getDateOfHoliday();
		String convertedDate = DateTimeToString.convertToString(dt);
		model.addAttribute("ph",ph_Edit);
		model.addAttribute("convertedDate", convertedDate);
		return "editpublicholiday";
	}
	
	
	@PostMapping("/updatepublicholiday")
	public String updatepublicholiday(String phID, String phName, String phDate, String phDescription) {
		_publicHolidayService.updatePublicHoliday(phID, phName, phDate, phDescription);
		return "redirect:/admin/viewpublicholidays";
	}
	
	@GetMapping("/deletepublicholiday")
	public String updatepublicholiday(String idHoliday) {
		_publicHolidayService.deletePublicHoliday(idHoliday);
		return "redirect:/admin/viewpublicholidays";
	}
	
	
}