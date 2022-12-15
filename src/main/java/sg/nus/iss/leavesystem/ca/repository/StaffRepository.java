package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Component;
import sg.nus.iss.leavesystem.ca.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
