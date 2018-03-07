package com.myorg.ticketservice.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.myorg.ticketservice.common.config.AppConfig;
import com.myorg.ticketservice.common.util.ConfirmationNumberGenerator;
import com.myorg.ticketservice.common.util.DateUtil;

/**
 * unit tests for <code>TicketRepositoryImpl</code>
 *
 * @author arshad.azeem
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketRepositoryImplTest {

  @Mock
  private AppConfig appConfig;

  @Mock
  private DateUtil dateUtil;

  @Mock
  private ConfirmationNumberGenerator confirmationNumberGenerator;

  private TicketRepository ticketRepository;

  /* constant for num seats */
  private static final int NUM_SEATS = 100;

  @Before
  public void setup() {
    this.ticketRepository = new TicketRepositoryImpl(this.appConfig, this.dateUtil, this.confirmationNumberGenerator);
  }

  /**
   * test method to return numSeatsAvailable
   *
   * @return
   */
  @Test
  public void givenValidInput_whenMethodCalled_returnAvailSeats() {

    when(this.appConfig.getMaxSeatsInVenue()).thenReturn(NUM_SEATS);

    assertEquals(NUM_SEATS, this.ticketRepository.numSeatsAvailable());

  }

}
