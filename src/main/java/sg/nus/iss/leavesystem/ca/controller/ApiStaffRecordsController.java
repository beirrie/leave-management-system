package sg.nus.iss.leavesystem.ca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.service.LeaveSchemeService;
import sg.nus.iss.leavesystem.ca.service.StaffService;

@Tag(description = "Staff resouces that provides access to availabe staff leave data", name = "Staff Resource")
@RestController
@RequestMapping("/api")
public class ApiStaffRecordsController {
	
	@Autowired
	public StaffService _StaffService;
	
	@GetMapping("/viewemployee/{staff_id}")
	public ResponseEntity<Staff> getAllLeaveSchemes(@PathVariable String staff_id) {
		try {
		Staff targetStaff = _StaffService.findStaffByID(staff_id);
		if (targetStaff == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(targetStaff, HttpStatus.CREATED);			
		}
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);			
		}

	}
	
//    @GetMapping("/announcements")
//    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
//        try {
//            List<Announcement> announcements = new ArrayList<Announcement>();
//            announcements = announcementService.getAnnouncementList();
//
//            if (announcements.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(announcements, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
