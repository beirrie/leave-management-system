package sg.nus.iss.leavesystem.ca.repository;

import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;

import java.util.List;

public interface OverTimeApplicationRepository extends JpaRepository<OvertimeApplication, Long>{

    @Query("SELECT e FROM OvertimeApplication e where e.employee.manager.id = " +
            ":id")
    List<OvertimeApplication> findByManager(@Param("id") Long id);

    @Query("SELECT e FROM OvertimeApplication e where e.employee.id = " + ":id")
    List<OvertimeApplication> findByStaff(Long id);
}
