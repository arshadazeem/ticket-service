package com.myorg.ticketservice.common.enums;

/**
 * enum for seat status
 *
 * @author arshad.azeem
 *
 */
public enum SeatStatus {

  AVAILABLE("A", "Seat Available"), HOLD("H", "Seat Hold"), RESERVED("R", "Seat Reserved"),;

  private String statusCode;
  private String description;

  private SeatStatus(final String statusCode, final String description) {
    this.statusCode = statusCode;
    this.description = description;
  }

  public String getStatusCode() {
    return this.statusCode;
  }

  public String getDescription() {
    return this.description;
  }

}
