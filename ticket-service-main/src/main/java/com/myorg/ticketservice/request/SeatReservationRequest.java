package com.myorg.ticketservice.request;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Request object for SeatReservation
 *
 * @author arshad.azeem
 *
 */
public class SeatReservationRequest {

  @Nonnull
  @Nonnegative
  private int seatHoldId;

  @Nonnull
  private String customerEmail;

  public int getSeatHoldId() {
    return this.seatHoldId;
  }

  public void setSeatHoldId(final int seatHoldId) {
    this.seatHoldId = seatHoldId;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public void setCustomerEmail(final String customerEmail) {
    this.customerEmail = customerEmail;
  }

}
