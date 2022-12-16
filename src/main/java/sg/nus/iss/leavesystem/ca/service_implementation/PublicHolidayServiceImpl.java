package sg.nus.iss.leavesystem.ca.service_implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Resource;
import sg.nus.iss.leavesystem.ca.model.PublicHoliday;
import sg.nus.iss.leavesystem.ca.repository.PublicHolidayRepository;
import sg.nus.iss.leavesystem.ca.service.PublicHolidayService;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
	
	@Resource
	public PublicHolidayRepository publicHolidayRepo;
	
	@Override
	public PublicHoliday getPublicHoliday(Long id) {
		// TODO Auto-generated method stub
		return publicHolidayRepo.findById(id).orElse(null);
	}
	
	@Override
	public List<PublicHoliday> getAllPublicHolidays() {
		// TODO Auto-generated method stub
		return publicHolidayRepo.findAll();
	}

	@Override
	public PublicHoliday getPublicHolidayByID(Long publicHolidayID) {
		// TODO Auto-generated method stub
		return publicHolidayRepo.findById(publicHolidayID).orElse(null);
	}
	
	@Override
	public void pullPublicHolidaysData() throws JsonMappingException, JsonProcessingException{
		int year = LocalDateTime.now().getYear();
		String url = "https://calendarific.com/api/v2/holidays?&"
				+ "api_key=73725c50c9a569f34dd03b3639cf962c35846e02&country=SG&year=" + year;
		RestTemplate restTemplate= new RestTemplate();
		String publicHolidays = restTemplate.getForObject(url, String.class);
		JsonNode jsonNode = new ObjectMapper().readTree(publicHolidays); 
		JsonNode newJsonNode = jsonNode.get("response").get("holidays");
		List<PublicHoliday> sgPublicHolidays = new ArrayList<>();
		for (JsonNode individualHoliday: newJsonNode) {
			if(individualHoliday.get("type").toString().equals("[\"National holiday\"]")) {	
				String holidayName = individualHoliday.get("name").toString();
				holidayName = holidayName.substring(1, holidayName.length()-1);
				int holidayYear = Integer.parseInt(individualHoliday.get("date").get("datetime").get("year").toString());
				int holidayMonth = Integer.parseInt(individualHoliday.get("date").get("datetime").get("month").toString());
				int holidayDay = Integer.parseInt(individualHoliday.get("date").get("datetime").get("day").toString());
				String holidayDescription = individualHoliday.get("description").toString();
				holidayDescription = holidayDescription.substring(1, holidayDescription.length()-1);
				LocalDateTime dt = LocalDateTime.of(holidayYear, holidayMonth, holidayDay, 0 , 0, 0);
				PublicHoliday newPH = new PublicHoliday(holidayName, dt, holidayDescription);
				publicHolidayRepo.saveAndFlush(newPH);	
			}
		}
	}

	@Override
	public PublicHoliday createPublicHoliday(String publicHolidayName, String dateOfHoliday, String description) {
		// TODO Auto-generated method stub
		int year = Integer.parseInt(dateOfHoliday.substring(0, 4));
		int month = Integer.parseInt(dateOfHoliday.substring(5, 7));
		int day = Integer.parseInt(dateOfHoliday.substring(8, 10));
		LocalDateTime dt = LocalDateTime.of(year, month, day, 0 , 0, 0);
		PublicHoliday newPH = new PublicHoliday(publicHolidayName, dt, description);
		return publicHolidayRepo.saveAndFlush(newPH);
	}

	@Override
	public void deleteAllPublicHolidaysData() {
		publicHolidayRepo.deleteAll();
		// TODO Auto-generated method stub
		
	}
	
	
	
}
