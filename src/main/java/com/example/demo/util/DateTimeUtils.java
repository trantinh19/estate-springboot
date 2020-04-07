package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT_WITH_TIME = "yyyy-MM-dd hh:mm:ss";

    public static Date convertStringToDate(String dateInString) {
        if (StringUtils.isEmpty(dateInString)) return null;

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            logger.debug("Error parse date", e);
        }
        return date;
    }

    public static Date convertStringToDateWithTime(String dateInString) {
        if (StringUtils.isEmpty(dateInString)) return null;

        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT_WITH_TIME);
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            logger.debug("Error parse date", e);
        }
        return date;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        return format.format(date);
    }

    public static String convertDateWithTimeToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE_FORMAT_WITH_TIME);
        return format.format(date);
    }

}
