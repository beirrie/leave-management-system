package sg.nus.iss.leavesystem.ca.service;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;

import java.util.List;

public interface LeaveSchemeService {

    List<LeaveScheme> getAllLeaveScheme();
    
    LeaveScheme getLeaveSchemeByID(Long leaveSchemeID);
    
    LeaveScheme createLeaveScheme(LeaveScheme newLeaveScheme);
    
    LeaveScheme updateLeaveScheme(String id, String schemeName, String annualLeave, String medicalLeave);
    
    void deactivateLeaveScheme(String id);
}
