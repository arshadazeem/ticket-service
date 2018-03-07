package com.myorg.ticketservice.common.dto;

import java.io.Serializable;

/**
 * Encapsulates error details
 *
 * @author arshad.azeem
 *
 */
public class ErrorDetail implements Serializable {

  /* serial id */
  private static final long serialVersionUID = 3363918296106283529L;

  private final int errorCode;
  private final String errorDescription;

  public ErrorDetail(final int errorCode, final String errorDescription) {
    this.errorCode = errorCode;
    this.errorDescription = errorDescription;
  }

  public int getErrorCode() {
    return this.errorCode;
  }

  public String getErrorDescription() {
    return this.errorDescription;
  }

}
