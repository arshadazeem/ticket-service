package com.myorg.ticketservice.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myorg.ticketservice.common.dto.ErrorDetail;
import com.myorg.ticketservice.common.dto.SeatHold;
import com.myorg.ticketservice.common.util.TicketUtil;
import com.myorg.ticketservice.exception.ApplicationException;
import com.myorg.ticketservice.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  @Autowired
  private TicketUtil ticketUtil;

  @Autowired
  public TicketServiceImpl(final TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public int numSeatsAvailable() {
    /*
     * Note: Could be whatever business logic that determines number of available seats
     * I am using repository, but just embedded some logic in there.
     */
    return this.ticketRepository.numSeatsAvailable();
  }

  @Override
  public SeatHold findAndHoldSeats(final int numSeats, @NotNull final String customerEmail) {
    /*
     * Note: Could be whatever business logic that determines best available seats
     * I am using repository, but just generating a random integer
     */
    final int numSeatsAvailable = this.numSeatsAvailable();

    if (!this.ticketUtil.isEligibleForHold(numSeats, numSeatsAvailable)) {
      // throw custom exception
    }

    return this.ticketRepository.findAndHoldSeats(numSeats, customerEmail);
  }

  @Override
  public String reserveSeats(final int seatHoldId, final String customerEmail) {

    final SeatHold seatHold = this.ticketRepository.getSeatHoldByEmail(customerEmail);

    if (this.ticketUtil.isSeatHoldExpired(seatHold)) {
      final ErrorDetail errorDetail = new ErrorDetail(100, "Seat Hold Expired");
      throw new ApplicationException(errorDetail);
    }

    /* reserve seats and return confirmation number */
    return this.ticketRepository.reserveSeats(seatHoldId, customerEmail);
  }

}
