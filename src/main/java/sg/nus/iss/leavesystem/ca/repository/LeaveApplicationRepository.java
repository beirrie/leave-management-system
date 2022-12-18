package sg.nus.iss.leavesystem.ca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>{
	
    @Query("SELECT l FROM LeaveApplication l WHERE l.employee.id = ?1")
    List<LeaveApplication> FindByUserId(long userId);

}
