package sg.nus.iss.leavesystem.ca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Component;
import sg.nus.iss.leavesystem.ca.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

	@Query("SELECT DISTINCT s FROM Staff s, Staff m WHERE s.id = m.manager.id")
	List<Staff> findAllStaff();

	@Query("SELECT DISTINCT s FROM Staff s, Staff m WHERE s.id = m.manager.id")
	List<Staff> findAllManagers();

	@Query("SELECT s FROM Staff s WHERE s.id = :sid")
	Staff findStaffByID(@Param("sid") String id);
}
