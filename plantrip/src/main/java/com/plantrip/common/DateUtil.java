package com.plantrip.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static LocalDateTime str2LocalDateTime(String str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(str, formatter);
    }

    public static LocalDateTime str2LocalDateTime(String str) {
        String tempStr = str.replaceAll("[^0-9]", "");
        if (tempStr.length() == 8) {
            tempStr += "000000";
        }

        if (tempStr.length() == 14) {
            return str2LocalDateTime(tempStr, "yyyyMMddHHmmss");
        }
        return null;
    }


    public static String localDateTime2str(LocalDateTime localDateTime, String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String localDateTime2strYYYYMMDD(LocalDateTime localDateTime) {
        return localDateTime2str(localDateTime, "yyyyMMdd");
    }


}
