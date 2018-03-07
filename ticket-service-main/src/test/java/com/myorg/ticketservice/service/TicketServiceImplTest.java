package com.myorg.ticketservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myorg.ticketservice.repository.TicketRepository;

/**
 * unit tests for <code>TicketServiceImpl</code>
 * 
 * @author arshad.azeem
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {

  @Mock
  private TicketRepository ticketRepository;
  private TicketService ticketService;

  private static final int expectedNumSeatsAvailable = 100;

  @Before
  public void setup() {
    this.ticketService = new TicketServiceImpl(this.ticketRepository);
  }

  @Test
  public void givenSeatsExist_whenNumSeatsAvailableCalled_thenReturnAvailableSeats() {

    when(this.ticketRepository.numSeatsAvailable()).thenReturn(expectedNumSeatsAvailable);

    final int actualNumSeatsAvailable = this.ticketService.numSeatsAvailable();

    assertEquals(expectedNumSeatsAvailable, actualNumSeatsAvailable);

    verify(this.ticketRepository).numSeatsAvailable();

  }

}
