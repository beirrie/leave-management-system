package sg.nus.iss.leavesystem.ca.model.dto;

import java.util.ArrayList;
import java.util.List;

import sg.nus.iss.leavesystem.ca.model.Role;

public class UserStaffForm {
	private Long userId;
	private String userName = "";
	private String password = "";
	private String employeeId = "";
	private List<Role> roles = new ArrayList<>();

	private String staffId = "";
	private String firstName = "";
	private String lastName = "";
	private String emailAdd = "";
	private String managerId = "";
	private String leaveSchemeId = "";

	public UserStaffForm() {
	}

	public UserStaffForm(String userName, String password, String employeeId, List<Role> roles, String staffId,
			String firstName, String lastName, String emailAdd, String managerId,
			String leaveSchemeId) {
		this.userName = userName;
		this.password = password;
		this.employeeId = employeeId;
		this.roles = roles;

		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.managerId = managerId;
		this.leaveSchemeId = leaveSchemeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getLeaveSchemeId() {
		return leaveSchemeId;
	}

	public void setLeaveSchemeId(String leave) {
		this.leaveSchemeId = leave;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdd() {
		return emailAdd;
	}

	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String id) {
		this.staffId = id;
	}
}
