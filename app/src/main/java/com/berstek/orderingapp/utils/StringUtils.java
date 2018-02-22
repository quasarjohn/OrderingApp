package com.berstek.orderingapp.utils;

import java.text.DecimalFormat;

public class StringUtils {

  public static String formatDf00(double d) {
    DecimalFormat df = new DecimalFormat("0.00");
    return df.format(d);
  }

  public static String getPesoSign() {
    return "₱";
  }

  public static String removeDecimalIfZero(String s) {
    String[] parts = s.split("\\.");
    if (parts[1].equals("00"))
      return parts[0];
    return s;
  }

  public static String formatWithPesoSign(double p) {
    return getPesoSign() + " " + removeDecimalIfZero(formatDf00(p));
  }
}
