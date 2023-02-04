package com.withdraw.utils;

import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class DateUtil {
    public static DateTimeFormatter DATE_TIME_ZONE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss.SSSSSS Z");

}
