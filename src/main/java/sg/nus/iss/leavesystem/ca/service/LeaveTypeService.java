package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import sg.nus.iss.leavesystem.ca.model.LeaveType;

public interface LeaveTypeService {
	
    List<LeaveType> GetAllWithoutCompensation();

    List<LeaveType> GetAll();

    LeaveType findById(long id);
}
