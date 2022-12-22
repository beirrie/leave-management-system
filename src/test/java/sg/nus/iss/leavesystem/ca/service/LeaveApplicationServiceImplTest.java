package sg.nus.iss.leavesystem.ca.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.test.annotation.DirtiesContext;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.repository.LeaveApplicationRepository;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@ExtendWith(MockitoExtension.class)
public class LeaveApplicationServiceImplTest {

    @InjectMocks
    private LeaveApplicationServiceImpl leaveAppServiceImpl;
    private LeaveApplication appliedLeave1, appliedLeave2, updatedLeave1, updatedLeave2, cancelledLeave1, rejectedLeave1, approvedLeave1;
    private Staff manager1, staff1;

    @Mock
    private LeaveApplicationRepository leaveAppRepo;

    @BeforeEach
    public void setup() {

        leaveAppServiceImpl = new LeaveApplicationServiceImpl(leaveAppRepo);

        manager1 = new Staff();
        manager1.setFirstName("Esther");
        manager1.setLastName("Tan");
        manager1.setEmailAdd("estherTan@nus.edu.sg");

        staff1 = new Staff();
        staff1.setFirstName("Staff 1");
        staff1.setLastName("Staff 1 last Name");
        staff1.setEmailAdd("staff1@nus.edu.sg");

        appliedLeave1 = new LeaveApplication();
        appliedLeave1.setApplicationStatus("Applied");
        appliedLeave1.setEmployeeManager(manager1);
        appliedLeave1.setEmployee(staff1);

        appliedLeave2 = new LeaveApplication();
        appliedLeave2.setApplicationStatus("Applied");
        appliedLeave2.setEmployeeManager(manager1);
        appliedLeave2.setEmployee(staff1);

        updatedLeave1 = new LeaveApplication();
        updatedLeave1.setApplicationStatus("Updated");
        updatedLeave1.setEmployeeManager(manager1);
        updatedLeave1.setEmployee(staff1);

        updatedLeave2 = new LeaveApplication();
        updatedLeave2.setApplicationStatus("Updated");
        updatedLeave2.setEmployeeManager(manager1);
        updatedLeave2.setEmployee(staff1);

        cancelledLeave1 = new LeaveApplication();
        cancelledLeave1.setApplicationStatus("Cancelled");
        cancelledLeave1.setEmployeeManager(manager1);
        cancelledLeave1.setEmployee(staff1);

        rejectedLeave1 = new LeaveApplication();
        rejectedLeave1.setApplicationStatus("Rejected");
        rejectedLeave1.setEmployeeManager(manager1);
        rejectedLeave1.setEmployee(staff1);

        approvedLeave1 = new LeaveApplication();
        approvedLeave1.setApplicationStatus("Approved");
        approvedLeave1.setEmployeeManager(manager1);
        approvedLeave1.setEmployee(staff1);

    }

    @Test
    public void givenManager_getLeaveApps() {
        given(leaveAppRepo.findAll()).willReturn(List.of(appliedLeave1, appliedLeave2, updatedLeave1, rejectedLeave1));
        List<LeaveApplication> leaveAppList = leaveAppServiceImpl.getStaffLeavesByManager(manager1);
        assertThat(leaveAppList.size()).isEqualTo(4);
    }

    @Test
    public void givenManagerAndLeaveStatus_getPendingLeaveApps() {
        given(leaveAppRepo.findAll()).willReturn(List.of(appliedLeave1, appliedLeave2, updatedLeave1, rejectedLeave1));
        List<LeaveApplication> leaveAppList = leaveAppServiceImpl.getStaffLeavesByManagerAndStatus(manager1, "Applied Updated");
        assertThat(leaveAppList.size()).isEqualTo(3);
    }

    @Test
    public void givenstaffId_getLeaveApps() {
        given(leaveAppRepo.findAll()).willReturn(List.of(appliedLeave1, updatedLeave1));
        List<LeaveApplication> leaveAppList = leaveAppServiceImpl.getStaffLeavesById(staff1.getId());
        assertThat(leaveAppList.size()).isEqualTo(2);
    }

    @Test
    public void givenstaffIdAndLeaveStatus_getLeaveApps() {
        given(leaveAppRepo.findAll()).willReturn(List.of(appliedLeave1, updatedLeave1));
        List<LeaveApplication> leaveAppList = leaveAppServiceImpl.getStaffLeavesByIdAndStatus(staff1.getId(), "Applied");
        assertThat(leaveAppList.size()).isEqualTo(1);
    }

    @Test
    public void givenAppliedStatusLeave_setApprovalStatusToApproved_returnApprovedStatus() {
        leaveAppServiceImpl.setApprovalStatus(appliedLeave1, "Approved", "You need a good rest", manager1);
        assertThat(appliedLeave1.getApplicationStatus()).isEqualTo("Approved");
    }

}
