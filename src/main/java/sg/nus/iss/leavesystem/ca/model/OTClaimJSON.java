package sg.nus.iss.leavesystem.ca.model;


public class OTClaimJSON {

    private Long employee_id;
    
    private String employee_name;

    private String date_OT; //

    private double hours_OT; //

    private String employeeComment; //

    private String appliedDateTime; //
    
    public OTClaimJSON() {
    	
    }

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getDate_OT() {
		return date_OT;
	}

	public void setDate_OT(String date_OT) {
		this.date_OT = date_OT;
	}

	public double getHours_OT() {
		return hours_OT;
	}

	public void setHours_OT(double hours_OT) {
		this.hours_OT = hours_OT;
	}

	public String getEmployeeComment() {
		return employeeComment;
	}

	public void setEmployeeComment(String employeeComment) {
		this.employeeComment = employeeComment;
	}

	public String getAppliedDateTime() {
		return appliedDateTime;
	}

	public void setAppliedDateTime(String appliedDateTime) {
		this.appliedDateTime = appliedDateTime;
	}
    
    

}
