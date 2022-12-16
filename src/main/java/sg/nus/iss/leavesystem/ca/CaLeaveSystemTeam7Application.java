package sg.nus.iss.leavesystem.ca;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.Role;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.repository.RoleRepository;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;
import sg.nus.iss.leavesystem.ca.repository.UserRepository;

@SpringBootApplication
public class CaLeaveSystemTeam7Application {

    public static void main(String[] args) {
        SpringApplication.run(CaLeaveSystemTeam7Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRun(UserRepository userRepo, StaffRepository staffRepo,
                                            LeaveSchemeRepository LeaveSchemeRepo, RoleRepository roleRepo) {
        return args -> {

            // Add leave schemes
            LeaveScheme ls1 = LeaveSchemeRepo.saveAndFlush(new LeaveScheme("Professional", 18, 60));
            LeaveScheme ls2 = LeaveSchemeRepo.saveAndFlush(new LeaveScheme("Administrative", 14, 60));

            Role admin = roleRepo.saveAndFlush(new Role("admin", "insert description of admin"));
            Role employee = roleRepo.saveAndFlush(new Role("employee", "insert description of employee"));
            Role manager = roleRepo.saveAndFlush(new Role("manager", "insert description of manager"));

            User testuser1 = new User("Robert", "password123");
            testuser1.addRole(manager);
            User testuser2 = userRepo.saveAndFlush(new User("Albert", "password123"));
            userRepo.saveAndFlush(testuser1);

            Staff teststaff1 = staffRepo.saveAndFlush(new Staff("Robert", "Lin", "robert@email.com", ls1, testuser1));
            Staff teststaff2 = staffRepo.saveAndFlush(new Staff("Albert", "Lin", "robert@email.com", ls2, testuser2));

            List<Staff> myStaffs = staffRepo.findAll();
            myStaffs.forEach(myStaff -> System.out.println(myStaff));
        };
    }
}
