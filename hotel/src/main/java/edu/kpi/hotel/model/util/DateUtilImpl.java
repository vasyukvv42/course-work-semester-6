package edu.kpi.hotel.model.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class DateUtilImpl implements DateUtil {

    private static final int EXPIRATION_DAYS_DELTA = -1;

    @Override
    public Date getExpiryDate(Date reserveFrom) {
        var calendar = Calendar.getInstance();
        calendar.setTime(reserveFrom);
        calendar.add(Calendar.DATE, EXPIRATION_DAYS_DELTA);
        return calendar.getTime();
    }

    @Override
    public int getDifferenceInDays(Date from, Date to) {
        long diff = to.getTime() - from.getTime();
        var convert = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        var ceil = (int) Math.ceil(convert);
        return ceil > 0 ? ceil : 1;
    }
}