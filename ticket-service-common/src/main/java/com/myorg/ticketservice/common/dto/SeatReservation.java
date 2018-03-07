package com.myorg.ticketservice.common.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Seats that have been successfully reserved
 *
 * @author arshad.azeem
 *
 */
public class SeatReservation implements Serializable {

  /** serial id */
  private static final long serialVersionUID = 1L;

  private String reservationId;
  private List<Seat> seats;

  public String getReservationId() {
    return this.reservationId;
  }

  public void setReservationId(final String reservationId) {
    this.reservationId = reservationId;
  }

  public List<Seat> getSeats() {
    return this.seats;
  }

  public void setSeats(final List<Seat> seats) {
    this.seats = seats;
  }

}
