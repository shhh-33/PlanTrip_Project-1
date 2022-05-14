package com.plantrip.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // String 으로 되어있는 날짜&시간을 실제 LocalDateTiem 타입으로 바꾸는 메서드
    public static LocalDateTime str2LocalDateTime(String str, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(str, formatter);
    }

    public static LocalDateTime str2LocalDateTime(String str) {
        // String 타입의 날짜 표현 파라미터로 받음 = str
        // 정규표현식 [^0-9] : 0~9사이의 숫자를 제외한 모든 문자(a-z,A-Z)는 공백으로 치환한다
        String tempStr = str.replaceAll("[^0-9]", "");
        //년월일 8자리 포맷인 경우, 뒤에 시간 표현 00:00:00 을 더해 아래와 같은 형태로 출력
        if (tempStr.length() == 8) {
            tempStr += "000000";
        }
        //년월일시분초 14자리 포맷인 경우, 아래와 같은 형태로 출력
        if (tempStr.length() == 14) {
            return str2LocalDateTime(tempStr, "yyyyMMddHHmmss");
        }
        return null;
    }

    // 현재 시각을 해당 format에 맞춰 출력
    public static String localDateTime2str(LocalDateTime localDateTime, String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }

    public static String localDateTime2strYYYYMMDD(LocalDateTime localDateTime) {
        return localDateTime2str(localDateTime, "yyyyMMdd");
    }


}
