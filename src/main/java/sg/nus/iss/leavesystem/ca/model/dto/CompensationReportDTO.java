package sg.nus.iss.leavesystem.ca.model.dto;

public class CompensationReportDTO {

    private String employeeName;
    private Long staffId;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
