package com.myorg.ticketservice.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myorg.ticketservice.common.config.AppConfig;
import com.myorg.ticketservice.common.dto.SeatHold;

@Component
public class TicketUtil {

  /* the application config */
  private final AppConfig appConfig;

  /* date util */
  private final DateUtil dateUtil;

  @Autowired
  public TicketUtil(final AppConfig appConfig, final DateUtil dateUtil) {
    this.appConfig = appConfig;
    this.dateUtil = dateUtil;
  }

  /**
   * performs basic sanity/business rule checks to ensure seat hold eligibility
   *
   * @param numSeatsToBeHeld the number of seats to be held
   * @param numSeatsAvailabe the total number of available seats
   * @return boolean indicating hold eligibility
   */
  public boolean isEligibleForHold(final int numSeatsToBeHeld, final int numSeatsAvailable) {

    /** Any other business logic could go in here, this is mine for now */
    if (this.appConfig.isTicketsAvailableForSale() && numSeatsToBeHeld > 0 && numSeatsAvailable >= numSeatsToBeHeld) {
      return true;
    }
    return false;
  }

  /**
   * has the hold on seat expired
   *
   * @param seatHold the seatHold to be inspected
   * @return boolean indicating if the seat hold has expired
   */
  public boolean isSeatHoldExpired(final SeatHold seatHold) {

    /* if seat timer hasn't expired */
    if ((this.dateUtil.getCurrentTimeInMillis() - seatHold.getSeatHeldTimeInMillis()) > this.appConfig
        .getSeatsHoldExpirationTimeInSeconds()) {
      return true;
    }
    return false;
  }

}
