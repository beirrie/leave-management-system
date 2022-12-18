package sg.nus.iss.leavesystem.ca.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.APIKeyService;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;
import sg.nus.iss.leavesystem.ca.service.StaffService;
import sg.nus.iss.leavesystem.ca.util.CheckOT;
import sg.nus.iss.leavesystem.ca.util.StringToDateTime;

@Tag(description = "Compensation application resource allows an OT claim to be made via API", name = "Compensation Application Resource")
@RestController
@RequestMapping("/api")
public class ApiOTClaimApplicationController {
	
	@Autowired
	APIKeyService _APIKeyService;
	
	@Autowired
	StaffService _StaffService;
	
	@Autowired
	OvertimeApplicationService _OTService;
	
	@GetMapping("/SubmitOTClaim")
	public ResponseEntity<OvertimeApplication> SubmitOTClaim(@RequestParam String authKey, 
			@RequestParam String staffID, String OT_Date, String OT_hours, String remarks) {
		Boolean authcheck = _APIKeyService.getAPIKeyByID(authKey);
		System.out.println(authcheck);
		Double ot = CheckOT.ValidOrNot(OT_hours);
		LocalDateTime inputDate = StringToDateTime.convertStringDT(OT_Date);
		Staff targetStaff = _StaffService.findStaffByID(staffID);
		try {
			if(authcheck == false) {
				System.out.println("Authentication unsuccessful!");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if (targetStaff == null) {
				System.out.println("No such employee");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if (ot == null) {
				System.out.println("Invalid OT hours must be bigger than 0 and in multiple of 0.5");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			_OTService.newAPIApplication(targetStaff, inputDate, ot, remarks);
			System.out.println("Successfully created");
			return new ResponseEntity<>(HttpStatus.CREATED);	
		}
		catch(Exception e){
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
//		private Staff employee;
//		
//		private LocalDateTime date_OT; 
//		
//		private double hours_OT; 
//		
//		private String employeeComment; 
//		
//		private LocalDateTime appliedDateTime; 

	}
};