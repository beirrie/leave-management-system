package sg.nus.iss.leavesystem.ca.repository;

import org.hibernate.sql.ast.tree.expression.Over;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OvertimeApplicationRepositoryTest {
    @Autowired
    TestEntityManager entityManager;
    @Autowired
    OverTimeApplicationRepository overtimeRepo;
    @Autowired
    StaffRepository staffRepo;
    @BeforeEach
    void setUp() {
        Staff testmanager1 = new Staff();
        Staff testemployee1 = new Staff();
        Staff testemployee2 = new Staff();

        testemployee1.setFirstName("test1");
        testemployee2.setFirstName("test2");
        testemployee1.setManager(testmanager1);
        testemployee2.setManager(testmanager1);

        List<Staff> staffList = new ArrayList<>(
            Arrays.asList(testemployee1, testemployee2, testmanager1));
        staffRepo.saveAll(staffList);

        OvertimeApplication otapp1 = new OvertimeApplication(testemployee1,
                LocalDateTime.now(), 1.0, "employee comment");
        OvertimeApplication otapp2 = new OvertimeApplication(testemployee2,
                LocalDateTime.now(), 2.0, "employee comment");
        OvertimeApplication otapp3 = new OvertimeApplication(testemployee2,
                LocalDateTime.now(), 3.0, "employee comment");

        List<OvertimeApplication> otList = new ArrayList<>(
                Arrays.asList(otapp1, otapp2, otapp3));
        overtimeRepo.saveAll(otList);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByManager() {
    }

    @Test
    void findByStaff() {
    }
}
