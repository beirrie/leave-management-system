package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import sg.nus.iss.leavesystem.ca.model.LeaveScheme;
import sg.nus.iss.leavesystem.ca.model.PublicHoliday;

public interface PublicHolidayService {

    PublicHoliday getPublicHoliday(Long id);

    List<PublicHoliday> getAllPublicHolidays();

    PublicHoliday getPublicHolidayByID(Long publicHolidayID);

    void pullPublicHolidaysData() throws JsonMappingException, JsonProcessingException;

    PublicHoliday createPublicHoliday(String publicHolidayName,
            String dateOfHoliday, String description);

    void deleteAllPublicHolidaysData();
}
