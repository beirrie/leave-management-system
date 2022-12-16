package sg.nus.iss.leavesystem.ca.model.dto;

public class LeaveApprovalDTO {

    private String applicationStatus;
    private Long approverId;
    private String approverRemark;

    public LeaveApprovalDTO(String applicationStatus, Long approverId, String approverRemark) {
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

    public void setApproveRemark(String approveRemark) {
        this.approverRemark = approveRemark;
    }
}
