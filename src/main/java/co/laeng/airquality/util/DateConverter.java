package co.laeng.airquality.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    public static String toISO8601(String datetime) {
        String date = datetime.substring(0, 8);
        String time = timeFormat(datetime.substring(8));
        return getISO8601(date, time).format(DateTimeFormatter.ISO_DATE_TIME);
    }

    private static String timeFormat(String time) {
        if (time.length() == 2) {
            time += "00";
        }

        return time;
    }

    private static ZonedDateTime getISO8601(String date, String time) {
        LocalDateTime local = LocalDateTime.parse(
                date + time,
                DateTimeFormatter.ofPattern("yyyyMMddHHmm")
        );

        return ZonedDateTime.of(local, ZoneId.of("Asia/Seoul"));
    }
}
