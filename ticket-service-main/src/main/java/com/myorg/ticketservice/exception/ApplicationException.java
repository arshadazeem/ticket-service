package com.myorg.ticketservice.exception;

import com.myorg.ticketservice.common.dto.ErrorDetail;

public class ApplicationException extends RuntimeException {

  /* default serializtion id */
  private static final long serialVersionUID = -2807554424362214154L;

  private final ErrorDetail errorDetail;

  /**
   *
   * @param message
   * @param cause
   * @param errorDetail
   */
  public ApplicationException(final String message, final Throwable cause, final ErrorDetail errorDetail) {
    super(message, cause);
    this.errorDetail = errorDetail;
  }

  /**
   *
   * @param errorDetail
   */
  public ApplicationException(final ErrorDetail errorDetail) {
    this.errorDetail = errorDetail;
  }

  /**
   *
   * @return
   */
  public ErrorDetail getErrorDetail() {
    return this.errorDetail;
  }


}
