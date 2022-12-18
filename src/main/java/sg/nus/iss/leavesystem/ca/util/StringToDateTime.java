package sg.nus.iss.leavesystem.ca.util;

import java.time.LocalDateTime;

public class StringToDateTime {

public static LocalDateTime convertStringDT(String inputStringDate) {
	
	int day =  Integer.parseInt(inputStringDate.substring(0, 2));
	int month =  Integer.parseInt(inputStringDate.substring(3, 5));
	int year =  Integer.parseInt(inputStringDate.substring(6, 10));
	LocalDateTime dt = LocalDateTime.of(year, month, day, 0 , 0, 0);
	return dt;
	
	}
}
