package com.myorg.ticketservice.common.dto;

import javax.validation.constraints.NotNull;

/**
 * Holds seat information
 *
 * @author arshad.azeem
 *
 */
public class Seat {

  @NotNull
  private String seatId;

  @NotNull
  private Section section;

  @NotNull
  private Row row;

  public String getSeatId() {
    return this.seatId;
  }

  public void setSeatId(final String seatId) {
    this.seatId = seatId;
  }

  public Section getSection() {
    return this.section;
  }

  public void setSection(final Section section) {
    this.section = section;
  }

  public Row getRow() {
    return this.row;
  }

  public void setRow(final Row row) {
    this.row = row;
  }

}
