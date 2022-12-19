package sg.nus.iss.leavesystem.ca.model.dto;

public class StaffForm {

	private String staffId = "";
	private String firstName = "";
	private String lastName = "";
	private String emailAdd = "";
	private String managerId = "";
	private String leaveSchemeId = "";
	private String isActive = "true";

	public StaffForm() {
	}

	public StaffForm(String staffId, String firstName, String lastName, String emailAdd, String managerId,
			String leaveSchemeId, String isActive) {
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAdd = emailAdd;
		this.managerId = managerId;
		this.leaveSchemeId = leaveSchemeId;
		this.isActive = isActive;
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

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
