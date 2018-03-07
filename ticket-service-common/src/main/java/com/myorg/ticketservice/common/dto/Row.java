package com.myorg.ticketservice.common.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Holds row information
 *
 * @author arshad.azeem
 *
 */
public class Row {

  @NotNull
  private String rowId;

  @Null
  private String rowName;

  public String getRowId() {
    return this.rowId;
  }

  public void setRowId(final String rowId) {
    this.rowId = rowId;
  }

  public String getRowName() {
    return this.rowName;
  }

  public void setRowName(final String rowName) {
    this.rowName = rowName;
  }

}
