package com.myorg.ticketservice.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.ticketservice.common.dto.ErrorDetail;
import com.myorg.ticketservice.common.dto.SeatHold;
import com.myorg.ticketservice.exception.ApplicationException;
import com.myorg.ticketservice.request.FindAndHoldServiceRequest;
import com.myorg.ticketservice.request.SeatReservationRequest;
import com.myorg.ticketservice.response.FindAndHoldServiceResponse;
import com.myorg.ticketservice.response.SeatAvailabilityResponse;
import com.myorg.ticketservice.response.SeatReservationResponse;
import com.myorg.ticketservice.service.TicketService;

/**
 * Rest Controller for ticket related operations
 *
 * @author arshad.azeem
 *
 */
@RestController
public class TicketController {

  private final TicketService ticketService;

  @Autowired
  public TicketController(final TicketService ticketService) {
    this.ticketService = ticketService;
  }

  /**
   * endpoint to get num seats available
   *
   * @return the <code>SeatAvailabilityResponse</code>
   */
  @ResponseStatus(OK)
  @RequestMapping(value = "/tickets/availability/count", method = GET, produces = APPLICATION_JSON_VALUE)
  public SeatAvailabilityResponse getNumSeatsAvailable() {
    final int numSeatsAvailable = this.ticketService.numSeatsAvailable();
    final SeatAvailabilityResponse response = new SeatAvailabilityResponse();
    response.setNumSeatsAvailable(numSeatsAvailable);

    return response;

  }

  /**
   * end point to find best available seats, and hold them
   *
   * @param request the requestbody
   * @return the response <code>FindAndHoldServiceResponse<code>
   */
  @ResponseStatus(OK)
  @RequestMapping(value = "/tickets/find-and-hold", method = POST, consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public FindAndHoldServiceResponse findAndHold(final @RequestBody FindAndHoldServiceRequest request) {

    /* basic validation */
    if (StringUtils.isBlank(request.getCustomerEmail()) || request.getNumSeats() < 1) {
      final ErrorDetail errorDetail = new ErrorDetail(400, "Invalid Input");
      throw new ApplicationException(errorDetail);
    }

    final SeatHold seatHold = this.ticketService.findAndHoldSeats(request.getNumSeats(), request.getCustomerEmail());
    final FindAndHoldServiceResponse response = new FindAndHoldServiceResponse();
    response.setSeatHold(seatHold);

    return response;

  }

  /**
   * end point to reserve the <code>SeatHold</code>
   *
   * @param request the requestbody
   * @return the response <code>FindAndHoldServiceResponse<code>
   */
  @ResponseStatus(OK)
  @RequestMapping(value = "/tickets/reserve", method = POST, consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public SeatReservationResponse reserveSeatHold(final @RequestBody SeatReservationRequest request) {

    final String confirmationNumber =
        this.ticketService.reserveSeats(request.getSeatHoldId(), request.getCustomerEmail());

    final SeatReservationResponse response = new SeatReservationResponse();
    response.setConfirmationNumber(confirmationNumber);

    return response;

  }

}
