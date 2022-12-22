package sg.nus.iss.leavesystem.ca.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.leavesystem.ca.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("SELECT DISTINCT s FROM Staff s, Staff m WHERE s.id = m.manager.id")
	List<Staff> findAllStaff();

	@Query("SELECT DISTINCT s FROM Staff s, Staff m WHERE s.id = m.manager.id")
	List<Staff> findAllManagers();

	@Query("SELECT s FROM Staff s WHERE s.id = :sid")
	Staff findStaffByID(@Param("sid") String id);
	
	@Query("SELECT u FROM Staff u WHERE u.user.id = ?1")
	Optional<Staff> FindByUserId(long userId);
  
	@Query("SELECT s FROM Staff s WHERE s.user.id <> :sid")
	List<Staff> findAllStaffExcludeID(@Param("sid") String id);

	List<Staff> findByManager_Id(Long id);





}
