package sg.nus.iss.leavesystem.ca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.Staff;
import sg.nus.iss.leavesystem.ca.model.User;
import sg.nus.iss.leavesystem.ca.model.dto.StaffForm;
import sg.nus.iss.leavesystem.ca.model.dto.UserStaffForm;
import sg.nus.iss.leavesystem.ca.repository.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffRepository staffRepository;

	@Autowired
	private LeaveSchemeService leaveSchemeService;

	@Transactional
	@Override
	public List<Staff> findAllStaff() {
		return staffRepository.findAll();
	}

	@Transactional
	@Override
	public Staff findStaffByID(Long id) {
		return staffRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Staff findStaffByID(String id) {
		Long staffId = Long.parseLong(id);
		return staffRepository.findById(staffId).orElse(null);
	}

	@Override
	public Staff FindByUserId(long userId) {
		return staffRepository.FindByUserId(userId).get();
	}

	@Transactional
	@Override
	public Staff createStaff(Staff staff) {
		return staffRepository.saveAndFlush(staff);
	}

	@Transactional
	@Override
	public Staff editStaff(String id, UserStaffForm userStaffForm) {
		Staff staff = findStaffByID(id);
		User user = staff.getUser();

		staff.setFirstName(userStaffForm.getFirstName());
		staff.setLastName(userStaffForm.getLastName());
		staff.setEmailAdd(userStaffForm.getEmailAdd());
		staff.setAnnualLeaveBalance(userStaffForm.getAnnualLeaveBalance());
		staff.setMedicalLeaveBalance(userStaffForm.getMedicalLeaveBalance());

		LeaveScheme leaveScheme = leaveSchemeService
				.getLeaveSchemeByID(Long.parseLong(userStaffForm.getLeaveSchemeId()));
		staff.setLeaveScheme(leaveScheme);
		Staff manager = findStaffByID(userStaffForm.getManagerId());
		staff.setManager(manager);

		user.setRoleSet(userStaffForm.getRoles());

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
			staffList.add(
					new StaffForm(
							s.getId().toString(),
							s.getFirstName(),
							s.getLastName(),
							s.getEmailAdd(),
							managerId,
							s.getLeaveScheme().getId().toString(),
							Boolean.toString(s.getIsActive())));
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
							s.getLeaveScheme().getId().toString(),
							Boolean.toString(s.getIsActive())));
		}
		return staffList;
	}

	@Transactional
	@Override
	public Boolean deactivateStaff(Staff staff) {
		staff.setIsActive(false);
		return staff.getIsActive();
	}

	@Transactional
	@Override
	public Boolean activateStaff(Staff staff) {
		staff.setIsActive(true);
		return staff.getIsActive();
	}
}