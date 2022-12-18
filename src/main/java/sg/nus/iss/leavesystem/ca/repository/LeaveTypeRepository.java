package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.leavesystem.ca.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

}
