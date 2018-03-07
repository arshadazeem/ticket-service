package com.myorg.ticketservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Response object for seat availability
 *
 * @author arshad.azeem
 *
 */
@JsonInclude(Include.NON_NULL)
public class SeatAvailabilityResponse extends TicketServiceResponse {

  private int numSeatsAvailable;

  public int getNumSeatsAvailable() {
    return this.numSeatsAvailable;
  }

  public void setNumSeatsAvailable(final int numSeatsAvailable) {
    this.numSeatsAvailable = numSeatsAvailable;
  }

  @Override
  public String toString() {
    return "SeatAvailabilityResponse [numSeatsAvailable=" + this.numSeatsAvailable + "]";
  }

}
