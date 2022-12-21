package sg.nus.iss.leavesystem.ca.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.LeaveApplication;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.io.IOException;
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
                        , app.getStartAM_or_PM(), app.getEndDate(), app.getEndAM_or_PM(), app.getPeriod(),
                        app.getCoveringStaff().getName(), app.getIsAbroad().toString(), app.getApplicationStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

