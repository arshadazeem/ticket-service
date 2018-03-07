package com.myorg.ticketservice.repository;

import java.util.List;

import com.myorg.ticketservice.common.dto.Seat;
import com.myorg.ticketservice.common.dto.SeatHold;


public interface TicketRepository {

  /**
   * The number of seats in the venue that are neither held nor reserved
   *
   * @return the number of tickets available in the venue
   */
  int numSeatsAvailable();

  /**
   * Find and hold the best available seats for a customer
   *
   * @param numSeats the number of seats to find and hold
   * @param customerEmail unique identifier for the customer
   * @return a SeatHold object identifying the specific seats and related
   *         information
   */
  SeatHold findAndHoldSeats(int numSeats, String customerEmail);

  /**
   * Commit seats held for a specific customer
   *
   * @param seatHoldId the seat hold identifier
   * @param customerEmail the email address of the customer to which the
   *        seat hold is assigned
   * @return a reservation confirmation code
   */
  String reserveSeats(int seatHoldId, String customerEmail);

  /**
   * removes seats from inventory
   *
   * @param seats list of seats to be removed
   */
  void removeSeatsFromInventory(final List<Seat> seats);

  /**
   * retrieves seat hold by email
   *
   * @param customerEmail the email associated with seat hold
   * @return the <code>SeatHold</code>
   */
  SeatHold getSeatHoldByEmail(String customerEmail);

}
