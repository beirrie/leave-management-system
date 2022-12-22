package sg.nus.iss.leavesystem.ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;

import java.util.List;

public interface OverTimeApplicationRepository extends JpaRepository<OvertimeApplication, Long> {
    @Query("select o from OvertimeApplication o where o.employee.manager.id = ?1 and o.applicationStatus = ?2")
    List<OvertimeApplication> findByEmployee_Manager_IdAndApplicationStatus(Long id, String applicationStatus);

    @Query("SELECT e FROM OvertimeApplication e where e.employee.manager.id = " +
            ":id")
    List<OvertimeApplication> findByManager(@Param("id") Long id);

    @Query("SELECT e FROM OvertimeApplication e where e.employee.id = :id ORDER BY e.date_OT DESC")
    List<OvertimeApplication> findByStaff(Long id);

    List<OvertimeApplication> findByEmployee_Manager_IdAndEmployee_OvertimeApplicationRecords_ApplicationStatus(Long id, String applicationStatus);
}
