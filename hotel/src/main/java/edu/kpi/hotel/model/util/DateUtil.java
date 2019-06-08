package edu.kpi.hotel.model.util;

import java.util.Date;

public interface DateUtil {
    Date getExpiryDate(Date reserveFrom);

    int getDifferenceInDays(Date from, Date to);
}
