package sg.nus.iss.leavesystem.ca.util;

import java.time.LocalDateTime;

public class StringToDateTimeYYYYMMDD {
	
	public static LocalDateTime convertYYYYMMDD_DT(String inputDate) {
		int year = Integer.parseInt(inputDate.substring(0, 4));
		int month = Integer.parseInt(inputDate.substring(5, 7));
		int day = Integer.parseInt(inputDate.substring(8, 10));
		LocalDateTime dt = LocalDateTime.of(year, month, day, 0 , 0, 0);
		return dt;
	}

}
