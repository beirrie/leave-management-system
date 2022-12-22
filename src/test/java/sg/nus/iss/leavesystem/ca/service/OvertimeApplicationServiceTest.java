package sg.nus.iss.leavesystem.ca.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.OverTimeApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(MockitoExtension.class)
class OvertimeApplicationServiceTest {

    @InjectMocks
    OvertimeApplicationServiceImpl overtimeApplicationService;

    @Mock
    OvertimeApplication otApp1, otApp2, otApp3;
    @Mock
    Staff testmanager1, testemployee1, testemployee2;
    @Mock
    OverTimeApplicationRepository overtimeApplicationRepo;

    @BeforeEach
    void Before() {

        testmanager1 = new Staff();
        testemployee1 = new Staff();
        testemployee2 = new Staff();

        testmanager1.setId(1L);
        testmanager1.setFirstName("manager1");
        testmanager1.setLastName("manager1");
        testmanager1.setEmailAdd("manager1@test1.com");

        testemployee1.setId(2L);
        testemployee1.setFirstName("test1");
        testemployee1.setLastName("test1");
        testemployee1.setEmailAdd("test1@test1.com");
        testemployee2.setManager(testmanager1);

        testemployee2.setId(3L);
        testemployee2.setFirstName("test2");
        testemployee2.setLastName("test2");
        testemployee2.setEmailAdd("test1@test2.com");
        testemployee2.setManager(testmanager1);

        otApp1 = new OvertimeApplication();
        otApp1.setId(1L);
        otApp1.setEmployee(testemployee1);
        otApp1.setAppliedDateTime(LocalDateTime.now());
        otApp1.setHours_OT(1.0);
        otApp1.setEmployeeComment("Employee comment");

        otApp2 = new OvertimeApplication();
        otApp2.setId(2L);
        otApp2.setEmployee(testemployee1);
        otApp2.setAppliedDateTime(LocalDateTime.now());
        otApp2.setHours_OT(1.0);
        otApp2.setEmployeeComment("Employee comment");

        otApp3 = new OvertimeApplication();
        otApp3.setId(3L);
        otApp3.setEmployee(testemployee2);
        otApp3.setAppliedDateTime(LocalDateTime.now());
        otApp3.setHours_OT(1.0);
        otApp3.setEmployeeComment("Employee comment");
    }

    @Test
    void getAllOvertimeApplication() {
        when(overtimeApplicationRepo.findAll()).thenReturn(List.of(otApp1, otApp2, otApp3));

        List<OvertimeApplication> retrievedList = overtimeApplicationService.getAllOvertimeApplication();

        assertEquals(3, retrievedList.size());
    }

    @Test
    void getAllByManager() {

        when(overtimeApplicationRepo.findByManager(testmanager1.getId())).thenReturn(List.of(otApp1, otApp2, otApp3));

        List<OvertimeApplication> retrievedList = overtimeApplicationService.getAllByManager(testmanager1);

        assertEquals(3, retrievedList.size());
    }

    @Test
    void getAllByStaff() {

        when(overtimeApplicationRepo.findByStaff(testmanager1.getId())).thenReturn(List.of(otApp1, otApp2));

        List<OvertimeApplication> retrievedList = overtimeApplicationService.getAllByStaff(testmanager1);

        assertEquals(2, retrievedList.size());
    }

    @Test
    void getById() {

        when(overtimeApplicationService.getById(1L)).thenReturn(otApp1);

        OvertimeApplication retrievedApp = overtimeApplicationService.getById(1L);

        assertEquals(otApp1, retrievedApp);
    }

    @Test
    void newApplication() {
        overtimeApplicationService.newApplication(otApp1);
    }

    @Test
    void setApprovalStatus() {
        overtimeApplicationService.setApprovalStatus(otApp1, "Success", "Manager Comment", testmanager1);
    }


}