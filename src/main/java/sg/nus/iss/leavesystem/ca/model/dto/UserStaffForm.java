package sg.nus.iss.leavesystem.ca.model.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import sg.nus.iss.leavesystem.ca.model.Role;

public class UserStaffForm {
	private Long userId;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 25, message = "Name must be 3-25 characters long")
	private String userName = "";

	@NotBlank(message = "Password is required")
	private String password = "";

	private String employeeId = "";
	private List<Role> roles = new ArrayList<>();
	private String staffId = "";
	private String firstName = "";
	private String lastName = "";
	private String emailAdd = "";
	private String managerId = "";
	private String leaveSchemeId = "";
	private double annualLeaveBalance;
	private double medicalLeaveBalance;

	public UserStaffForm() {
	}

	public UserStaffForm(String userName, String password, String employeeId, List<Role> roles, String staffId,
			String firstName, String lastName, String emailAdd, String managerId,
			String leaveSchemeId, double annualLeaveBalance, double medicalLeaveBalance) {
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
		this.annualLeaveBalance = annualLeaveBalance;
		this.medicalLeaveBalance = medicalLeaveBalance;
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

	public double getAnnualLeaveBalance() {
		return annualLeaveBalance;
	}

	public void setAnnualLeaveBalance(double annualLeaveBalance) {
		this.annualLeaveBalance = annualLeaveBalance;
	}

	public double getMedicalLeaveBalance() {
		return medicalLeaveBalance;
	}

	public void setMedicalLeaveBalance(double medicalLeaveBalance) {
		this.medicalLeaveBalance = medicalLeaveBalance;
	}
}
