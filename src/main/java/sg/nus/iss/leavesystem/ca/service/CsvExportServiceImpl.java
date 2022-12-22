package sg.nus.iss.leavesystem.ca.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.OvertimeApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVFormat;
import sg.nus.iss.leavesystem.ca.util.DateTimeToString;

@Service
@Transactional
public class CsvExportServiceImpl implements CsvExportService {

    @Autowired
    LeaveApplicationService leaveApplicationService;

    @Autowired
    OvertimeApplicationService overtimeApplicationService;

    @Override
    public void leaveReportToCsv(Writer writer, Staff manager, Long staffId, String leaveTypeName, String startPeriod
            , String endPeriod) {
        List<LeaveApplication> leaveApplications = leaveApplicationService.getListForReport(manager.getId(), staffId,
                leaveTypeName, startPeriod, endPeriod);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Leave Id", "Employee Id", "Employee Name", "Leave Type", "Start Date",
                    "Start Time", "End Date", "End Time", "Duration", "Covering Staff", "Is Abroad",
                    "Status");
            for (LeaveApplication app : leaveApplications) { //columns
                csvPrinter.printRecord((app.getId()),app.getEmployee().getId(), app.getEmployee().getName(),
                        app.getTypeOfLeave().getLeaveTypeName(), DateTimeToString.convertToString(app.getStartDate())
                        , app.getStartAM_or_PM(), DateTimeToString.convertToString(app.getEndDate()),
                                app.getEndAM_or_PM(),
                        app.getPeriod(),
                        app.getCoveringStaff().getName(), app.getIsAbroad().toString(), app.getApplicationStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void compensationReportToCsv(PrintWriter writer, Staff manager, Long staffId) {
        List<OvertimeApplication> overtimeApplications = overtimeApplicationService.getListForReport(manager.getId(),
                staffId);

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Overtime Application Id", "Employee Id", "Employee Name", "Hours Overtime", "Date" +
                            " Overtime", "Employee Comment","Application Status", "Date Reviewed");
            for (OvertimeApplication app : overtimeApplications) { //columns
                csvPrinter.printRecord((app.getId()),app.getEmployee().getId(), app.getEmployee().getName(),
                        app.getHours_OT(), DateTimeToString.convertToString(app.getDate_OT()),
                        app.getEmployeeComment(), app.getApplicationStatus(),
                        DateTimeToString.convertToString(app.getDateApplicationReviewed()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

