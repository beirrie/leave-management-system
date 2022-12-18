package sg.nus.iss.leavesystem.ca.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@DataJpaTest
public class OvertimeApplicationRepositoryTest {
    List<OvertimeApplication> retrievedList;
    List<OvertimeApplication> inputList;
    Staff testmanager1;
    Staff testemployee1;
    Staff testemployee2;

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    OverTimeApplicationRepository overtimeRepo;

    @BeforeEach
    void Before() {

        testmanager1 = new Staff();
        testemployee1 = new Staff();
        testemployee2 = new Staff();

        testmanager1.setFirstName("manager1");
        testmanager1.setLastName("manager1");
        testmanager1.setEmailAdd("manager1@test1.com");

        testemployee1.setFirstName("test1");
        testemployee1.setLastName("test1");
        testemployee1.setEmailAdd("test1@test1.com");

        testemployee2.setFirstName("test2");
        testemployee2.setLastName("test2");
        testemployee2.setEmailAdd("test1@test2.com");
        testemployee2.setManager(testmanager1);

        entityManager.persist(testemployee1);
        entityManager.persist(testemployee2);
        entityManager.persist(testmanager1);

        entityManager.flush();


        OvertimeApplication otapp1 = new OvertimeApplication(testemployee1,
                LocalDateTime.now(), 1.0, "employee comment");
        OvertimeApplication otapp2 = new OvertimeApplication(testemployee2,
                LocalDateTime.now(), 2.0, "employee comment");
        OvertimeApplication otapp3 = new OvertimeApplication(testemployee2,
                LocalDateTime.now(), 3.0, "employee comment");

        inputList = new ArrayList<>(Arrays.asList(otapp2, otapp3));

        entityManager.persist(otapp1);
        entityManager.persist(otapp2);
        entityManager.persist(otapp3);

        entityManager.flush();
    }

    @Test
    void findByManager() {
        retrievedList =
                overtimeRepo.findByManager(testmanager1.getId());

        assertEquals(inputList, retrievedList);
    }

    @Test
    void findByStaff() {
        retrievedList =
                overtimeRepo.findByStaff(testemployee2.getId());

        assertEquals(inputList, retrievedList);
    }

    @Test
    void findById() {
        OvertimeApplication inputApp = new OvertimeApplication(testemployee1,
                LocalDateTime.now(), 1.0, "employee comment");

        overtimeRepo.save(inputApp);

        Optional<OvertimeApplication> retrievedApp =
                overtimeRepo.findById(inputApp.getId());

        assertTrue(retrievedApp.isPresent()
                && inputApp.equals(retrievedApp.get()));
    }
}
