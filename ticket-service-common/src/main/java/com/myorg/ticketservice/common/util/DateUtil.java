package com.myorg.ticketservice.common.util;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

  /**
   * gets the current time in milli seconds
   * 
   * @return current time in millis
   */
  public long getCurrentTimeInMillis() {
    return System.currentTimeMillis();
  }

}
