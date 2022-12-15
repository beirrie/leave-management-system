package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>{
	
	

}
