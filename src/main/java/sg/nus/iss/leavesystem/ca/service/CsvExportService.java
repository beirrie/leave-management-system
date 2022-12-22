package sg.nus.iss.leavesystem.ca.service;

import org.springframework.stereotype.Service;
import sg.nus.iss.leavesystem.ca.model.Staff;

import java.io.PrintWriter;
import java.io.Writer;

@Service
public interface CsvExportService {

    void leaveReportToCsv(Writer writer, Staff manager, Long staffId, String leaveTypeName, String startPeriod, String endPeriod);

    void compensationReportToCsv(PrintWriter writer, Staff manager, Long staffId);
}
