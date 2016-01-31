package de.gammadata.microservices.addressrs.application.control;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author GFR
 */
public class JacksonZuluDateSerializer extends JsonSerializer<Date> {

  private static DateFormat getDateFormat() {
    DateFormat zuluDateFormat;
    zuluDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.GERMANY);
    zuluDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    return zuluDateFormat;
  }
  
  /**
   *
   * @param date
   * @return
   */
  public static String serialize(Date date){
    return getDateFormat().format(date);
  }


  /**
   * For test purposes only.
   *
   * @param dateString String
   * @return Date
   * @throws java.text.ParseException
   */
  public static Date desializeDateString(String dateString) throws ParseException {
    if (dateString == null || dateString.length() == 0) {
      return null;
    } else {
      Date res = getDateFormat().parse(dateString);
      return res;
    }
  }
  /**
   *
   * @param date
   * @param jg
   * @param sp
   * @throws IOException
   * @throws JsonGenerationException
   */
  @Override
  public void serialize(Date date, JsonGenerator jg, SerializerProvider sp)
          throws IOException, JsonGenerationException {
    String dateString = serialize(date);
    jg.writeString(dateString);
  }
}
