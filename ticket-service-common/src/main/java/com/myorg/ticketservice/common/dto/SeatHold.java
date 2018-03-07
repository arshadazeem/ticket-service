package com.myorg.ticketservice.common.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.myorg.ticketservice.common.enums.SeatStatus;

/**
 * Object holds seats that are held to be reserved
 *
 * @author arshad.azeem
 *
 */
public class SeatHold implements Serializable {

  /** serial id */
  private static final long serialVersionUID = 1L;

  private int seatHoldId;
  private long seatHeldTimeInMillis;
  private SeatStatus seatStatus;
  private final List<Seat> seats = Collections.emptyList();

  /**
   * default constructor
   *
   * @param seatHoldId the unique seatHoldId for this seat hold
   * @param seatHeldTimeInMillis time (UTC) when seat hold was established
   */
  public SeatHold() {
    // default
  }

  /**
   * constructor
   *
   * @param seatHoldId the unique seatHoldId for this seat hold
   * @param seatHeldTimeInMillis time (UTC) when seat hold was established
   */
  public SeatHold(final int seatHoldId, final long seatHeldTimeInMillis) {
    this.seatHoldId = seatHoldId;
    this.seatHeldTimeInMillis = seatHeldTimeInMillis;
  }

  public int getSeatHoldId() {
    return this.seatHoldId;
  }

  public void setSeatHoldId(final int seatHoldId) {
    this.seatHoldId = seatHoldId;
  }

  public long getSeatHeldTimeInMillis() {
    return this.seatHeldTimeInMillis;
  }

  public void setSeatHeldTimeInMillis(final long seatHeldTimeInMillis) {
    this.seatHeldTimeInMillis = seatHeldTimeInMillis;
  }

  public List<Seat> getSeats() {
    return this.seats;
  }

  public int getNumSeats() {
    return this.getSeats().size();
  }

  public SeatStatus getSeatStatus() {
    return this.seatStatus;
  }

  public void setSeatStatus(final SeatStatus seatStatus) {
    this.seatStatus = seatStatus;
  }

  @Override
  public String toString() {
    return "SeatHold [seatHoldId=" + this.seatHoldId + ", seatHeldTimeInMillis=" + this.seatHeldTimeInMillis
        + ", seatStatus=" + this.seatStatus + ", seats=" + this.seats + "]";
  }

}
