package sg.nus.iss.leavesystem.ca.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

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
}
