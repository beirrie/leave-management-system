package sg.nus.iss.leavesystem.ca;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.LeaveSchemeRepository;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;
import sg.nus.iss.leavesystem.ca.service.OvertimeApplicationService;

import java.time.LocalDateTime;
import java.util.TimeZone;

@SpringBootApplication
public class CaLeaveSystemTeam7Application {

	public static void main(String[] args) {
		SpringApplication.run(CaLeaveSystemTeam7Application.class, args);
	}

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("SGT"));
	}

	@Bean
	CommandLineRunner initData(LeaveSchemeRepository leaveSchemeRepository,
							   StaffRepository staffRepository,
							   OvertimeApplicationService overtimeApplicationService
							   ) {
		return (args) -> {
			// Add leave schemes
			LeaveScheme ls1 =
					leaveSchemeRepository.saveAndFlush(new LeaveScheme(
					"Professional", 18, 60));
			LeaveScheme ls2 =
					leaveSchemeRepository.saveAndFlush(new LeaveScheme(
							"Administrative", 14, 60));

			// Add  staff
			Staff jon_snow = staffRepository.saveAndFlush(new Staff("Uzumaki",
					"Naruto", "uzumaki_naruto@gmail.com", ls1));
			Staff monkey_d_luffy = staffRepository.saveAndFlush(new Staff(
					"Monkey",
					"D. Luffy", "monkey_d_luffy@gmail.com", ls2));

			// Add Overtime application
			OvertimeApplication otapp1 =
					new OvertimeApplication
							(monkey_d_luffy, LocalDateTime.now(), LocalDateTime.now().plusHours(3),
									"test comment");

			overtimeApplicationService.newApplication(otapp1);

			OvertimeApplication otapp2 =
					new OvertimeApplication
							(monkey_d_luffy, LocalDateTime.now().plusHours(5),
									LocalDateTime.now().plusHours(8),
									"test comment");

			overtimeApplicationService.newApplication(otapp2);

			OvertimeApplication otapp3 =
					new OvertimeApplication
							(jon_snow, LocalDateTime.now().plusHours(6),
									LocalDateTime.now().plusHours(9),
									"test comment");

			overtimeApplicationService.newApplication(otapp3);

			// Approve/Reject Overtime application

				// Approve
				overtimeApplicationService.setApprovalStatus(otapp1, 1,
				"approve remark", jon_snow.getId());

				// Reject
				overtimeApplicationService.setApprovalStatus(otapp2, 2,
				"reject remark", jon_snow.getId());



		};
	}

}
