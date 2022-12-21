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

    // public static List<LocalDateTime> prepareHoliday() {
    //     holidays = new ArrayList<>();
    //     holidays.add(convertStringToDate("26/12/2022"));
    //     holidays.add(convertStringToDate("02/01/2023"));
    //     holidays.add(convertStringToDate("23/01/2023"));
    //     holidays.add(convertStringToDate("24/01/2023"));
    //     holidays.add(convertStringToDate("07/04/2023"));
    //     holidays.add(convertStringToDate("01/05/2023"));
    //     holidays.add(convertStringToDate("02/06/2023"));
    //     holidays.add(convertStringToDate("29/06/2023"));
    //     holidays.add(convertStringToDate("09/08/2023"));
    //     holidays.add(convertStringToDate("13/11/2023"));
    //     holidays.add(convertStringToDate("25/12/2023"));
    //     return holidays;
    // }

    public static Boolean isPublicHoliday(LocalDateTime date) {
        // if (holidays.size() == 0)
        //     holidays = prepareHoliday();

        // for (LocalDateTime localDateTime : holidays) {
        //     if (localDateTime.isEqual(date))
        //         return true;
        // }
        for(PublicHoliday ph : phs)
        {
            if(ph.getDateOfHoliday().isEqual(date))
                return true;
        }
        return false;
    }
}