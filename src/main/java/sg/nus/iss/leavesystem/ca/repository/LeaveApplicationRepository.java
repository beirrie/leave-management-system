package sg.nus.iss.leavesystem.ca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>{

}
