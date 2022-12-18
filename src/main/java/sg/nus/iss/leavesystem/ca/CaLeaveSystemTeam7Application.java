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
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.repository.APIKeyRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.repository.LeaveTypeRepository;
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
			APIKeyRepository APIKeyRepo) {
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

			Staff staff1 = staffRepo.saveAndFlush(new Staff("Robert", "Lin", "robert@email.com", ls1, user1));
			Staff staff2 = new Staff("Albert", "Tan", "albert@email.com", ls2, user2);
			staff2.setManager(staff1);
			staffRepo.saveAndFlush(staff2);

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
			leaveAppl.setDateReviewed(LocalDateTime.of(2022,12,13,0,0));
			leaveAppl.setMgrRemarks("okay");
			leaveApplicationRepo.saveAndFlush(leaveAppl);
			
			APIKey newAPIKey = new APIKey("Shaun", "Lin", "shanfu87@yahoo.com");
			APIKeyRepo.saveAndFlush(newAPIKey);
			System.out.println(newAPIKey.toString());
						
		};
	}
}
