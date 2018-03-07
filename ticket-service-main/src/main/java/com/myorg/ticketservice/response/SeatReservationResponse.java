package com.myorg.ticketservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Response object for seat reservation
 *
 * @author arshad.azeem
 *
 */
@JsonInclude(Include.NON_NULL)
public class SeatReservationResponse extends TicketServiceResponse {

  /* just for this exercise, would need lot more details like seat info in real scenarios */
  private String confirmationNumber;

  public String getConfirmationNumber() {
    return this.confirmationNumber;
  }

  public void setConfirmationNumber(final String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

}
