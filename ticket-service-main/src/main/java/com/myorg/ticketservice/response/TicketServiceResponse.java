package com.myorg.ticketservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Base response object for all ticket service responses
 */
@JsonInclude(Include.NON_NULL)
public class TicketServiceResponse {

  private Integer resultCode;
  private String resultDescription;
  private Integer errorCode;
  private String errorDescription;

  public Integer getResultCode() {
    return this.resultCode;
  }

  public void setResultCode(final Integer resultCode) {
    this.resultCode = resultCode;
  }

  public String getResultDescription() {
    return this.resultDescription;
  }

  public void setResultDescription(final String resultDescription) {
    this.resultDescription = resultDescription;
  }

  public Integer getErrorCode() {
    return this.errorCode;
  }

  public void setErrorCode(final Integer errorCode) {
    this.errorCode = errorCode;
  }

  public String getErrorDescription() {
    return this.errorDescription;
  }

  public void setErrorDescription(final String errorDescription) {
    this.errorDescription = errorDescription;
  }

}
