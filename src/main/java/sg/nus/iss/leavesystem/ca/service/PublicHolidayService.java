package sg.nus.iss.leavesystem.ca.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import sg.nus.iss.leavesystem.ca.model.PublicHoliday;

public interface PublicHolidayService {

    PublicHoliday getPublicHoliday(Long id);

    List<PublicHoliday> getAllPublicHolidays();

    PublicHoliday getPublicHolidayByID(Long publicHolidayID);

    void pullPublicHolidaysData() throws JsonProcessingException;

    PublicHoliday createPublicHoliday(String publicHolidayName,
            String dateOfHoliday, String description);

    void deleteAllPublicHolidaysData();
    
    PublicHoliday updatePublicHoliday(String phID, String phName, String phDate, String phDescription);
    
	void deletePublicHoliday(String phID);
}
