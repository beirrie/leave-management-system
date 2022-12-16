package sg.nus.iss.leavesystem.ca.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeToString {
	
	public static String convertToString(LocalDateTime dt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = dt.format(formatter);
        return formattedDateTime;
	}
}
