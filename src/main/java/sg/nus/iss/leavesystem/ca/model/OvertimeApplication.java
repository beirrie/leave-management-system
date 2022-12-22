package sg.nus.iss.leavesystem.ca.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonAutoDetect(fieldVisibility=Visibility.ANY)
@Entity
@Table(name = "overtime_records")
public class OvertimeApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    @ManyToOne
    @JoinColumn(name = "employee_Id", referencedColumnName = "id")
    private Staff employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date_OT; //

    private double hours_OT; //

    private String employeeComment; //

    private LocalDateTime appliedDateTime; //

    private String applicationStatus; //

    @ManyToOne
    @JoinColumn(name = "approverId", referencedColumnName = "id")
    private Staff approver;

    private String managerRemarks;//

    private LocalDateTime dateApplicationReviewed; //

    public OvertimeApplication() {
    }

    public OvertimeApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment) {
        this.employee = employee;
        this.date_OT = OT_Date;
        this.hours_OT = hours;
        this.employeeComment = employeeComment;
        this.appliedDateTime = LocalDateTime.now();
        this.applicationStatus = "Applied";
    }
    
    public OvertimeApplication(Staff employee, LocalDateTime OT_Date, double hours, String employeeComment, Staff approver) {
        this.employee = employee;
        this.approver = approver;
        this.date_OT = OT_Date;
        this.hours_OT = hours;
        this.employeeComment = employeeComment;
        this.appliedDateTime = LocalDateTime.now();
        this.applicationStatus = "Applied";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getEmployee() {
        return employee;
    }

    public void setEmployee(Staff employee) {
        this.employee = employee;
    }

    public String getEmployeeComment() {
        return employeeComment;
    }

    public void setEmployeeComment(String employeeComment) {
        this.employeeComment = employeeComment;
    }

    public LocalDateTime getAppliedDateTime() {
        return appliedDateTime;
    }

    public void setAppliedDateTime(LocalDateTime appliedDateTime) {
        this.appliedDateTime = appliedDateTime;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getManagerRemarks() {
        return managerRemarks;
    }

    public void setManagerRemarks(String managerRemarks) {
        this.managerRemarks = managerRemarks;
    }

    public LocalDateTime getDateApplicationReviewed() {
        return dateApplicationReviewed;
    }

    public void setDateApplicationReviewed(LocalDateTime dateApplicationReviewed) {
        this.dateApplicationReviewed = dateApplicationReviewed;
    }

    public Staff getApprover() {
        return approver;
    }

    public void setApprover(Staff approver) {
        this.approver = approver;
    }

    public LocalDateTime getDate_OT() {
        return date_OT;
    }

    public void setDate_OT(LocalDateTime date_OT) {
        this.date_OT = date_OT;
    }

    public double getHours_OT() {
        return hours_OT;
    }

    public void setHours_OT(double hours_OT) {
        this.hours_OT = hours_OT;
    }

    @Override
    public String toString() {
        return "OvertimeApplication{" +
                "id=" + id +
                ", employee=" + employee +
                ", date_OT=" + date_OT +
                ", hours_OT=" + hours_OT +
                ", employeeComment='" + employeeComment + '\'' +
                ", appliedDateTime=" + appliedDateTime +
                ", applicationStatus='" + applicationStatus + '\'' +
                ", approver=" + approver +
                ", managerRemarks='" + managerRemarks + '\'' +
                ", dateApplicationReviewed=" + dateApplicationReviewed +
                '}';
    }
}
