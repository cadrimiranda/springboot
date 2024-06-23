package com.dietreino.backend.utils;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class DateUtils {
    public static Date parse(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            return null;
        }

        Date date;

        try {
            Instant instant = Instant.parse(dateString);
            date = Date.from(instant);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            date = null;
        }

        return date;
    }
}
