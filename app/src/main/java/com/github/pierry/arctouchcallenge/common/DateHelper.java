package com.github.pierry.arctouchcallenge.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateHelper {

  private static Date parseDate(String date) {
    ArrayList<String> formats = new ArrayList<String>();
    formats.add("yyyy-MM-dd'T'HH:mm:ss'+0000'");
    Date extractedDate;
    for (String format : formats) {
      SimpleDateFormat dateFormat = new SimpleDateFormat(format);
      try {
        extractedDate = dateFormat.parse(date);
        return extractedDate;
      } catch (ParseException ignored) {
        System.out.println(ignored.getMessage());
      }
    }
    return null;
  }

  public static String date(String date) {
    try {
      Date parsedDate = parseDate(date);
      if (parsedDate == null) {
        return date;
      }
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM - HH:mm");
      String formatted = dateFormat.format(parsedDate);
      return formatted;
    } catch (Exception e) {
      return date;
    }
  }
}
