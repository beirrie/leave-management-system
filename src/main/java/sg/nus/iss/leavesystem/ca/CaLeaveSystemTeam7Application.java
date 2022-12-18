package sg.nus.iss.leavesystem.ca;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.leavesystem.ca.model.APIKey;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.LeaveType;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.repository.APIKeyRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveTypeRepository;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;
import sg.nus.iss.leavesystem.ca.repository.RoleRepository;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;
import sg.nus.iss.leavesystem.ca.repository.UserRepository;


@SpringBootApplication
public class CaLeaveSystemTeam7Application {

	public static void main(String[] args) {
		SpringApplication.run(CaLeaveSystemTeam7Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRun(
			UserRepository userRepo,
			StaffRepository staffRepo,
			LeaveSchemeRepository LeaveSchemeRepo,
			LeaveTypeRepository leaveTypeRepo,
			LeaveApplicationRepository leaveApplicationRepo,
			RoleRepository roleRepo,
			APIKeyRepository APIKeyRepo,
			OverTimeApplicationRepository otRepo) {
		return args -> {

			// Add leave schemes
			LeaveScheme ls1 = LeaveSchemeRepo.saveAndFlush(new LeaveScheme("Professional", 18, 60));
			LeaveScheme ls2 = LeaveSchemeRepo.saveAndFlush(new LeaveScheme("Administrative", 14, 60));

			LeaveType annual = leaveTypeRepo.saveAndFlush(new LeaveType("annual"));
			LeaveType medical = leaveTypeRepo.saveAndFlush(new LeaveType("medical"));
			LeaveType compensation = leaveTypeRepo.saveAndFlush(new LeaveType("compensation"));

			Role admin = roleRepo.saveAndFlush(new Role("admin", "insert description of admin"));
			Role employee = roleRepo.saveAndFlush(new Role("employee", "insert description of employee"));
			Role manager = roleRepo.saveAndFlush(new Role("manager", "insert description of manager"));

			User user1 = new User("Robert", "password123");
			user1.addRole(manager);
			User user2 = userRepo.saveAndFlush(new User("Albert", "albert123"));
			userRepo.saveAndFlush(user1);
			User user3 = new User("userSarah", "pw123");
			user3.addRole(manager);
			userRepo.saveAndFlush(user3);

			Staff staff1 = staffRepo.saveAndFlush(new Staff("Robert", "Lin", "robert@email.com", ls1, user1));
			Staff staff2 = new Staff("Albert", "Tan", "albert@email.com", ls2, user2);
			staff2.setManager(staff1);
			staffRepo.saveAndFlush(staff2);
			Staff staff3 = new Staff("Sarah", "Wong", "sarah@email.com", ls2, user3);
			staffRepo.saveAndFlush(staff3);
			staff1.setManager(staff3);
			staffRepo.saveAndFlush(staff1);

			List<Staff> myStaffs = staffRepo.findAll();
			myStaffs.forEach(myStaff -> System.out.println(myStaff));

			LeaveApplication leaveAppl = new LeaveApplication();
			leaveAppl.setEmployee(staff2);
			leaveAppl.setTypeOfLeave(annual);
			leaveAppl.setIsAbroad(true);
			leaveAppl.setContactNumber("99999999");
			leaveAppl.setCoveringStaff(staff1);
			leaveAppl.setStartDate(LocalDateTime.of(2022, 12, 27, 0, 0));
			leaveAppl.setStartAM_or_PM("");
			leaveAppl.setEndDate(LocalDateTime.of(2022, 12, 27, 0, 0));
			leaveAppl.setEndAM_or_PM("");
			leaveAppl.setAdditionalComments("additional comments");
			leaveAppl.setApplicationDate(LocalDateTime.of(2022, 12, 12, 0, 0));
			leaveAppl.setApplicationStatus("approved");
			leaveAppl.setEmployeeManager(staff1);
			leaveAppl.setDateReviewed(LocalDateTime.of(2022, 12, 13, 0, 0));
			leaveAppl.setMgrRemarks("okay");
			leaveApplicationRepo.saveAndFlush(leaveAppl);

			APIKey newAPIKey = new APIKey("Shaun", "Lin", "shanfu87@yahoo.com");
			APIKeyRepo.saveAndFlush(newAPIKey);
			System.out.println(newAPIKey.toString());

			LeaveApplication approvedAnnualLeave1 = new LeaveApplication();
			approvedAnnualLeave1.setEmployee(staff1);
			approvedAnnualLeave1.setTypeOfLeave(annual);
			approvedAnnualLeave1.setIsAbroad(false);
			approvedAnnualLeave1.setContactNumber("99999999");
			approvedAnnualLeave1.setCoveringStaff(staff2);
			approvedAnnualLeave1.setStartDate(LocalDateTime.of(2022, 12, 30, 0, 0));
			approvedAnnualLeave1.setStartAM_or_PM("AM");
			approvedAnnualLeave1.setEndDate(LocalDateTime.of(2022, 12, 30, 0, 0));
			approvedAnnualLeave1.setEndAM_or_PM("PM");
			approvedAnnualLeave1.setAdditionalComments("Clear annual Leave");
			approvedAnnualLeave1.setApplicationDate(LocalDateTime.of(2022, 12, 12, 0, 0));
			approvedAnnualLeave1.setApplicationStatus("Approved");
			approvedAnnualLeave1.setEmployeeManager(staff3);
			approvedAnnualLeave1.setDateReviewed(LocalDateTime.of(2022, 12, 13, 0, 0));
			approvedAnnualLeave1.setMgrRemarks("okay");
			leaveApplicationRepo.saveAndFlush(approvedAnnualLeave1);

			LeaveApplication approvedAnnualLeave2 = new LeaveApplication();
			approvedAnnualLeave2.setEmployee(staff2);
			approvedAnnualLeave2.setTypeOfLeave(annual);
			approvedAnnualLeave2.setIsAbroad(true);
			approvedAnnualLeave2.setContactNumber("99999911");
			approvedAnnualLeave2.setCoveringStaff(staff1);
			approvedAnnualLeave2.setStartDate(LocalDateTime.of(2022, 12, 26, 0, 0));
			approvedAnnualLeave2.setStartAM_or_PM("AM");
			approvedAnnualLeave2.setEndDate(LocalDateTime.of(2022, 12, 27, 0, 0));
			approvedAnnualLeave2.setEndAM_or_PM("PM");
			approvedAnnualLeave2.setAdditionalComments("Short getaway");
			approvedAnnualLeave2.setApplicationDate(LocalDateTime.of(2022, 12, 13, 0, 0));
			approvedAnnualLeave2.setApplicationStatus("Approved");
			approvedAnnualLeave2.setEmployeeManager(staff3);
			approvedAnnualLeave2.setDateReviewed(LocalDateTime.of(2022, 12, 14, 0, 0));
			approvedAnnualLeave2.setMgrRemarks("okay");
			leaveApplicationRepo.saveAndFlush(approvedAnnualLeave2);

			LeaveApplication appliedAnnualLeave1 = new LeaveApplication();
			appliedAnnualLeave1.setEmployee(staff1);
			appliedAnnualLeave1.setTypeOfLeave(annual);
			appliedAnnualLeave1.setIsAbroad(false);
			appliedAnnualLeave1.setContactNumber("99999999");
			appliedAnnualLeave1.setCoveringStaff(staff2);
			appliedAnnualLeave1.setStartDate(LocalDateTime.of(2023, 1, 5, 0, 0));
			appliedAnnualLeave1.setStartAM_or_PM("AM");
			appliedAnnualLeave1.setEndDate(LocalDateTime.of(2023, 1, 6, 0, 0));
			appliedAnnualLeave1.setEndAM_or_PM("PM");
			appliedAnnualLeave1.setAdditionalComments("Moving house");
			appliedAnnualLeave1.setApplicationDate(LocalDateTime.of(2022, 12, 16, 0, 0));
			appliedAnnualLeave1.setApplicationStatus("Applied");
			appliedAnnualLeave1.setEmployeeManager(staff3);
			// appliedAnnualLeave1.setDateReviewed(LocalDateTime.of(2022,12,16,0,0));
			appliedAnnualLeave1.setMgrRemarks("");
			leaveApplicationRepo.saveAndFlush(appliedAnnualLeave1);

			LeaveApplication appliedMedicalApplication1 = new LeaveApplication();
			appliedMedicalApplication1.setEmployee(staff2);
			appliedMedicalApplication1.setTypeOfLeave(medical);
			appliedMedicalApplication1.setIsAbroad(false);
			appliedMedicalApplication1.setContactNumber("99999911");
			appliedMedicalApplication1.setCoveringStaff(staff1);
			appliedMedicalApplication1.setStartDate(LocalDateTime.of(2023, 1, 11, 0, 0));
			appliedMedicalApplication1.setStartAM_or_PM("AM");
			appliedMedicalApplication1.setEndDate(LocalDateTime.of(2023, 1, 11, 0, 0));
			appliedMedicalApplication1.setEndAM_or_PM("PM");
			appliedMedicalApplication1.setAdditionalComments("Health checkup");
			appliedMedicalApplication1.setApplicationDate(LocalDateTime.of(2022, 12, 10, 0, 0));
			appliedMedicalApplication1.setApplicationStatus("Applied");
			appliedMedicalApplication1.setEmployeeManager(staff3);
			// appliedAnnualLeave1.setDateReviewed(LocalDateTime.of(2022,12,16,0,0));
			// appliedMedicalApplication.setMgrRemarks("okay");
			leaveApplicationRepo.saveAndFlush(appliedMedicalApplication1);

			LeaveApplication updatedMedleaveApplication1 = new LeaveApplication();
			updatedMedleaveApplication1.setEmployee(staff1);
			updatedMedleaveApplication1.setTypeOfLeave(medical);
			updatedMedleaveApplication1.setIsAbroad(false);
			updatedMedleaveApplication1.setContactNumber("99999999");
			updatedMedleaveApplication1.setCoveringStaff(staff2);
			updatedMedleaveApplication1.setStartDate(LocalDateTime.of(2023, 1, 25, 0, 0));
			updatedMedleaveApplication1.setStartAM_or_PM("AM");
			updatedMedleaveApplication1.setEndDate(LocalDateTime.of(2023, 1, 25, 0, 0));
			updatedMedleaveApplication1.setEndAM_or_PM("PM");
			updatedMedleaveApplication1.setAdditionalComments("Doctor appointment rescheduled");
			updatedMedleaveApplication1.setApplicationDate(LocalDateTime.of(2022, 12, 8, 0, 0));
			updatedMedleaveApplication1.setApplicationStatus("Updated");
			updatedMedleaveApplication1.setEmployeeManager(staff3);
			// updatedMedleaveApplication1.setDateReviewed(LocalDateTime.of(2022,12,13,0,0));
			updatedMedleaveApplication1.setMgrRemarks("");
			leaveApplicationRepo.saveAndFlush(updatedMedleaveApplication1);

			LeaveApplication rejectedLeaveApplication1 = new LeaveApplication();
			rejectedLeaveApplication1.setEmployee(staff1);
			rejectedLeaveApplication1.setTypeOfLeave(annual);
			rejectedLeaveApplication1.setIsAbroad(false);
			rejectedLeaveApplication1.setContactNumber("99999999");
			rejectedLeaveApplication1.setCoveringStaff(staff2);
			rejectedLeaveApplication1.setStartDate(LocalDateTime.of(2023, 1, 25, 0, 0));
			rejectedLeaveApplication1.setStartAM_or_PM("AM");
			rejectedLeaveApplication1.setEndDate(LocalDateTime.of(2023, 1, 27, 0, 0));
			rejectedLeaveApplication1.setEndAM_or_PM("PM");
			rejectedLeaveApplication1.setAdditionalComments("Clear annual Leave");
			rejectedLeaveApplication1.setApplicationDate(LocalDateTime.of(2022, 12, 5, 0, 0));
			rejectedLeaveApplication1.setApplicationStatus("Rejected");
			rejectedLeaveApplication1.setEmployeeManager(staff3);
			updatedMedleaveApplication1.setDateReviewed(LocalDateTime.of(2022, 12, 13, 0, 0));
			rejectedLeaveApplication1.setMgrRemarks("Sorry, urgent project deadline to meet");
			leaveApplicationRepo.saveAndFlush(rejectedLeaveApplication1);

			LeaveApplication rejectedLeaveApplication2 = new LeaveApplication();
			rejectedLeaveApplication2.setEmployee(staff2);
			rejectedLeaveApplication2.setTypeOfLeave(annual);
			rejectedLeaveApplication2.setIsAbroad(false);
			rejectedLeaveApplication2.setContactNumber("99999911");
			rejectedLeaveApplication2.setCoveringStaff(staff1);
			rejectedLeaveApplication2.setStartDate(LocalDateTime.of(2022, 11, 24, 0, 0));
			rejectedLeaveApplication2.setStartAM_or_PM("AM");
			rejectedLeaveApplication2.setEndDate(LocalDateTime.of(2022, 11, 24, 0, 0));
			rejectedLeaveApplication2.setEndAM_or_PM("PM");
			rejectedLeaveApplication2.setAdditionalComments("Personal matters");
			rejectedLeaveApplication2.setApplicationDate(LocalDateTime.of(2022, 11, 10, 0, 0));
			rejectedLeaveApplication2.setApplicationStatus("Rejected");
			rejectedLeaveApplication2.setEmployeeManager(staff1);
			updatedMedleaveApplication1.setDateReviewed(LocalDateTime.of(2022, 11, 15, 0, 0));
			rejectedLeaveApplication2.setMgrRemarks("Sorry, urgent project deadline to meet");
			leaveApplicationRepo.saveAndFlush(rejectedLeaveApplication2);

			LeaveApplication cancelledLeaveApplication1 = new LeaveApplication();
			cancelledLeaveApplication1.setEmployee(staff1);
			cancelledLeaveApplication1.setTypeOfLeave(annual);
			cancelledLeaveApplication1.setIsAbroad(false);
			cancelledLeaveApplication1.setContactNumber("99999999");
			cancelledLeaveApplication1.setCoveringStaff(staff2);
			cancelledLeaveApplication1.setStartDate(LocalDateTime.of(2022, 11, 2, 0, 0));
			cancelledLeaveApplication1.setStartAM_or_PM("AM");
			cancelledLeaveApplication1.setEndDate(LocalDateTime.of(2022, 11, 2, 0, 0));
			cancelledLeaveApplication1.setEndAM_or_PM("PM");
			cancelledLeaveApplication1.setAdditionalComments("Personal");
			cancelledLeaveApplication1.setApplicationDate(LocalDateTime.of(2022, 11, 3, 0, 0));
			cancelledLeaveApplication1.setApplicationStatus("Cancelled");
			cancelledLeaveApplication1.setEmployeeManager(staff3);
			updatedMedleaveApplication1.setDateReviewed(LocalDateTime.of(2022, 11, 3, 0, 0));
			cancelledLeaveApplication1.setMgrRemarks("ok");
			leaveApplicationRepo.saveAndFlush(cancelledLeaveApplication1);

			// OvertimeApplication ot1 = new OvertimeApplication();
			// ot1.setEmployee(staff1);
			// ot1.setAppliedDateTime(LocalDateTime.of(2022, 12, 25, 0, 0));
			// ot1.setApprover(manager1);
			// ot1.setDateApplicationReviewed(LocalDateTime.of(2022, 12, 26, 0, 0));
			// ot1.setApplicationStatus("Applied");
			// ot1.setDate_OT(LocalDateTime.of(2022, 12, 27, 0, 0, 0));
			// ot1.setEmployeeComment("I need to work");
			// ot1.setHours_OT(1);
			// ot1.setManagerRemarks("I allowed");

			OvertimeApplication otApp2 = new OvertimeApplication();
			otApp2.setEmployee(staff2);
			otApp2.setAppliedDateTime(LocalDateTime.now());
			otApp2.setDate_OT(LocalDateTime.of(2022, 12, 26, 0, 0));
			otApp2.setHours_OT(1.0);
			otApp2.setApplicationStatus("Applied");
			otApp2.setEmployeeComment("Employee comment2");

			OvertimeApplication otApp3 = new OvertimeApplication();
			otApp3.setEmployee(staff1);
			otApp3.setAppliedDateTime(LocalDateTime.now());
			otApp3.setDate_OT(LocalDateTime.of(2022, 12, 27, 0, 0));
			otApp3.setHours_OT(1.0);
			otApp3.setApplicationStatus("Applied");
			otApp3.setEmployeeComment("Employee comment3");

			OvertimeApplication otApp1 = new OvertimeApplication();
			otApp1.setEmployee(staff1);
			otApp1.setAppliedDateTime(LocalDateTime.now());
			otApp1.setDate_OT(LocalDateTime.of(2022, 12, 15, 0, 0));
			otApp1.setHours_OT(1.0);
			otApp1.setApplicationStatus("Approved");
			otApp1.setEmployeeComment("Employee comment");
			otApp1.setApprover(staff3);
			otApp1.setDateApplicationReviewed(LocalDateTime.of(2022, 12, 10, 0, 0, 0));
			otApp1.setManagerRemarks("So Far So Good1");
			otRepo.saveAndFlush(otApp3);
			otRepo.saveAndFlush(otApp2);
			otRepo.saveAndFlush(otApp1);

			// List<OvertimeApplication> otps = otRepo.findAll();

			// System.out.println("hihihihihihihihihihihihihihihihihihihihihih");
			// OvertimeApplicationService otservice = new OvertimeApplicationServiceImpl();
			// List<OvertimeApplication> ot = otservice.getAllOvertimeApplication();
			// otps.forEach(myot -> System.out.println(myot));
		};
	}
}
