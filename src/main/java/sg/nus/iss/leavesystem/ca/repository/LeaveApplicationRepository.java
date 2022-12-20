package sg.nus.iss.leavesystem.ca.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.nus.iss.leavesystem.ca.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    @Query("SELECT l FROM LeaveApplication l WHERE l.employee.id = ?1")
    List<LeaveApplication> FindByUserId(long userId);

    @Query("""
            select l from LeaveApplication l inner join l.employee.leaveApplicationRecords leaveApplicationRecords
            where l.employee.manager.id = ?1 and l.employee.id = ?2 and l.typeOfLeave.leaveTypeName = ?3 and leaveApplicationRecords.startDate >= ?4 and leaveApplicationRecords.endDate <= ?5""")
    List<LeaveApplication> findByFilter(Long id, Long id1, String leaveTypeName, LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
            select l from LeaveApplication l inner join l.employee.leaveApplicationRecords leaveApplicationRecords
            where l.employee.manager.id = ?1 and l.employee.id = ?2 and leaveApplicationRecords.startDate >= ?3 and leaveApplicationRecords.endDate <= ?4""")
    List<LeaveApplication> findForReportByStaffId(Long id, Long id1, LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
            select l from LeaveApplication l inner join l.employee.leaveApplicationRecords leaveApplicationRecords
            where l.employee.manager.id = ?1 and l.typeOfLeave.leaveTypeName = ?2 and leaveApplicationRecords.startDate >= ?3 and leaveApplicationRecords.endDate <= ?4""")
    List<LeaveApplication> findForReportByLeaveType(Long id, String leaveTypeName, LocalDateTime startDate, LocalDateTime endDate);

    @Query("""
            select l from LeaveApplication l inner join l.coveringStaff.leaveApplicationRecords leaveApplicationRecords
            where l.employee.manager.id = ?1 and leaveApplicationRecords.startDate >= ?2 and leaveApplicationRecords.endDate <= ?3""")
    List<LeaveApplication> findForReportByDate(Long id, LocalDateTime startDate, LocalDateTime endDate);









}
