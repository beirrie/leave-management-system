package sg.nus.iss.leavesystem.ca.model.dto;

public class OvertimeApprovalDTO {

    private String applicationStatus;
    private Long approverId;
    private String approverRemark;
    private Long otId;

    public OvertimeApprovalDTO() {

    }

    public OvertimeApprovalDTO(String applicationStatus, Long approverId, String approverRemark) {
        this.applicationStatus = applicationStatus;
        this.approverId = approverId;
        this.approverRemark = approverRemark;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public Long getApproverId() {
        return approverId;
    }

    public void setApproverId(Long approverId) {
        this.approverId = approverId;
    }

    public String getApproverRemark() {
        return approverRemark;
    }

    public void setApproverRemark(String approverRemark) {
        this.approverRemark = approverRemark;
    }

    public Long getOtId() {
        return otId;
    }

    public void setOtId(Long otId) {
        this.otId = otId;
    }
}
