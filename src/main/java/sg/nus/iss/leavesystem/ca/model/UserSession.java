package sg.nus.iss.leavesystem.ca.model;

public class UserSession {
	
    private long staffId;

    private String userName;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
