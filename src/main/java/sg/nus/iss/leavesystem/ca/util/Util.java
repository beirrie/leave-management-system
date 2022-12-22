package sg.nus.iss.leavesystem.ca.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import sg.nus.iss.leavesystem.ca.model.PublicHoliday;

public class Util {

    static List<LocalDateTime> holidays = new ArrayList<>();

    public static List<PublicHoliday> phs = new ArrayList<>();

    public Util() {

    }

    public static String convertDateToString(LocalDateTime localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    public static LocalDateTime convertStringToDate(String localDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String[] args = localDateStr.split("/");
        String dateStr = args[2] + "-" + args[1] + "-" + args[0] + " 00:00";
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static Boolean isWeekend(LocalDateTime date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public static Boolean isPublicHoliday(LocalDateTime date) {

        for(PublicHoliday ph : phs)
        {
            if(ph.getDateOfHoliday().isEqual(date))
                return true;
        }
        return false;
    }
}