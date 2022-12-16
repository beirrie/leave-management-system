package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.dto.StaffForm;

public interface StaffService {
	Staff createStaff(Staff staff);

	List<Staff> findAllStaff();

	Staff findStaffByID(String id);

	List<Staff> findAllManagers();

	List<StaffForm> getStaffList();

	List<StaffForm> getStaffFormManagers();

}
