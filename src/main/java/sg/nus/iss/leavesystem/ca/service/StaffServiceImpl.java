package sg.nus.iss.leavesystem.ca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.dto.StaffForm;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;
import sg.nus.iss.leavesystem.ca.repository.UserRepository;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	@Override
	public List<Staff> findAllStaff() {
		return staffRepository.findAll();
	}

	@Transactional
	@Override
	public Staff findStaffByID(Long id) {
		return staffRepository.findStaffByID(id);
	}

	@Transactional
	@Override
	public Staff findStaffByID(String id) {
		Long staffId = Long.parseLong(id);
		return staffRepository.findStaffByID(staffId);
	}

	@Transactional
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepository.saveAndFlush(staff);
	}

	@Transactional
	@Override
	public List<Staff> findAllManagers() {
		return staffRepository.findAllManagers();
	}

	@Transactional
	@Override
	public List<StaffForm> getStaffList() {
		List<Staff> staffs = staffRepository.findAll();
		List<StaffForm> staffList = new ArrayList<>();
		for (Staff s : staffs) {

			String managerId = "";
			if (s.getManager() != null) {
				managerId = s.getManager().getId().toString();
			}
			if (s.getIsActive()) {
				staffList.add(
						new StaffForm(
								s.getId().toString(),
								s.getFirstName(),
								s.getLastName(),
								s.getEmailAdd(),
								managerId,
								s.getLeaveScheme().getId().toString()));
			}
		}
		return staffList;
	}

	@Transactional
	@Override
	public List<StaffForm> getStaffFormManagers() {
		List<Staff> staffs = staffRepository.findAll();
		List<StaffForm> staffList = new ArrayList<>();
		for (Staff s : staffs) {
			String managerId = "";
			if (s.getManager() != null) {
				managerId = s.getManager().getId().toString();
			}
			staffList.add(
					new StaffForm(
							s.getId().toString(),
							s.getFirstName(),
							s.getLastName(),
							s.getEmailAdd(),
							managerId,
							s.getLeaveScheme().getId().toString()));
		}
		return staffList;
	}

	@Transactional
	@Override
	public Boolean deactivateStaff(Staff staff) {
		staff.setIsActive(false);
		return staff.getIsActive();
	}
}