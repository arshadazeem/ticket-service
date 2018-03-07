package com.myorg.ticketservice.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.annotations.VisibleForTesting;
import com.myorg.ticketservice.common.config.AppConfig;
import com.myorg.ticketservice.common.dto.Seat;
import com.myorg.ticketservice.common.dto.SeatHold;
import com.myorg.ticketservice.common.dto.SeatReservation;
import com.myorg.ticketservice.common.util.ConfirmationNumberGenerator;
import com.myorg.ticketservice.common.util.DateUtil;

/**
 * Repository for ticket operations
 *
 * @author arshad.azeem
 *
 */
@Repository
public class TicketRepositoryImpl implements TicketRepository {

  private final AppConfig appConfig;
  private final DateUtil dateUtil;
  private final ConfirmationNumberGenerator confirmationNumberGenerator;

  /* ONLY for the purpose of this exercise !!! */
  private final AtomicInteger heldSeatCount = new AtomicInteger();
  private final AtomicInteger reservedSeatCount = new AtomicInteger();
  private final AtomicInteger seatHoldId = new AtomicInteger();

  /* store all seat holds in a multi map */
  private final Map<String, SeatHold> seatsHeld = new ConcurrentHashMap<>();

  /* store all seat reservations in an immutable map */
  private final Map<String, SeatReservation> seatsReserved = new ConcurrentHashMap<>();

  /**
   * constructor
   */
  @Autowired
  public TicketRepositoryImpl(
      final AppConfig appConfig,
      final DateUtil dateUtil,
      final ConfirmationNumberGenerator confirmationNumberGenerator) {
    this.appConfig = appConfig;
    this.dateUtil = dateUtil;
    this.confirmationNumberGenerator = confirmationNumberGenerator;
  }

  @Override
  public int numSeatsAvailable() {

    /* For purpose of this exercise, do the math here */
    final int availSeats =
        this.appConfig.getMaxSeatsInVenue() - (this.heldSeatCount.get() + this.reservedSeatCount.get());

    return availSeats;
  }

  @Override
  public SeatHold findAndHoldSeats(final int numSeats, final String customerEmail) {
    /*
     * In a real app, persist seat hold in some persistent store (or cache)
     * For this exercise, i am setting in an immutable map.
     */
    this.heldSeatCount.addAndGet(numSeats);
    final SeatHold seatHold = new SeatHold(this.seatHoldId.incrementAndGet(), this.dateUtil.getCurrentTimeInMillis());
    this.seatsHeld.put(customerEmail, seatHold);

    return seatHold;
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {

    /* reserve and persist in persistent store - as an atomic transaction !!! */

    final SeatHold seatHold = this.seatsHeld.get(customerEmail);

    final String confirmationNumber = this.confirmationNumberGenerator.generateConfirmationNumber();

    /* create reservation/reserver */
    final SeatReservation seatReservation = this.createReservation(seatHold, confirmationNumber);
    this.seatsReserved.put(customerEmail, seatReservation);

    /* remove from currently held */
    this.seatsHeld.remove(customerEmail);

    /* remove seats from list of available inventory */
    this.removeSeatsFromInventory(seatHold.getSeats());

    return confirmationNumber;
  }

  @Override
  public SeatHold getSeatHoldByEmail(final String customerEmail) {
    return this.seatsHeld.get(customerEmail);
  }

  @Override
  public void removeSeatsFromInventory(final List<Seat> seats) {
    /* logic to remove seats from inventory */
  }

  @VisibleForTesting
  SeatReservation createReservation(final SeatHold seatHold, final String confirmationNumber) {
    final SeatReservation seatReservation = new SeatReservation();
    seatReservation.setReservationId(confirmationNumber);
    seatReservation.setSeats(seatHold.getSeats());
    return seatReservation;
  }

}
