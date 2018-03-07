package com.myorg.ticketservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.myorg.ticketservice.common.dto.SeatHold;

@JsonInclude(Include.NON_NULL)
public class FindAndHoldServiceResponse extends TicketServiceResponse {

  private SeatHold seatHold;

  public SeatHold getSeatHold() {
    return this.seatHold;
  }

  public void setSeatHold(final SeatHold seatHold) {
    this.seatHold = seatHold;
  }

  @Override
  public String toString() {
    return "FindAndHoldServiceResponse [seatHold=" + this.seatHold + "]";
  }

}
