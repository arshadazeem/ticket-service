package com.myorg.ticketservice.request;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * Request object for FindAndHoldService
 *
 * @author arshad.azeem
 *
 */
public class FindAndHoldServiceRequest {

  @Nonnull
  @Nonnegative
  private int numSeats;

  @Nonnull
  private String customerEmail;

  public int getNumSeats() {
    return this.numSeats;
  }

  public void setNumSeats(final int numSeats) {
    this.numSeats = numSeats;
  }

  public String getCustomerEmail() {
    return this.customerEmail;
  }

  public void setCustomerEmail(final String customerEmail) {
    this.customerEmail = customerEmail;
  }

}
