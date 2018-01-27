package com.berstek.orderingapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

  public static long getCurrentEpochDate() {
    SimpleDateFormat monthYear = new SimpleDateFormat("dd-MM-yyyy");
    String d = monthYear.format(new Date());
    Date yearDate = null;
    try {
      yearDate = monthYear.parse(d);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return yearDate.getTime();
  }
}
