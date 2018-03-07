package com.myorg.ticketservice.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.myorg.ticketservice.response.SeatAvailabilityResponse;

/**
 * Integration test for the NumSeatsAvailable service.
 *
 * @author arshad.azeem
 *
 */
public class NumSeatsAvailableIntegrationTest extends TicketServiceIntegrationTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * success test for seat availability count service
   */
  @Test
  public void givenValidEndpoint_whenAvailabilityCountServiceCalled_thenReturnAvailableSeats() {

    this.printMessage("NumSeatsAvailableIntegrationTest", "valid input");

    final URI numSeatsAvailableEndpoint = this.makeServiceUri(AVAILABILITY_SERVICE_ENDPOINT);

    final ResponseEntity<SeatAvailabilityResponse> responseEntity =
        this.restTemplate().getForEntity(numSeatsAvailableEndpoint, SeatAvailabilityResponse.class);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    assertNotNull(responseEntity.getBody());

    this.printDashedLine();
    System.out.println("Response received from Service: " + responseEntity.getBody());
    this.printDashedLine();

    assertTrue(responseEntity.getBody().getNumSeatsAvailable() >= 0);

  }

  /**
   * test for 404 at invalid endpoint
   */
  @Test
  public void givenInValidEndpoint_whenAvailabilityCountServiceCalled_thenReturnHttpClientErrorException() {

    this.printMessage("NumSeatsAvailableIntegrationTest", "Invalid Endpoint");

    final URI numSeatsAvailableEndpoint = this.makeServiceUri("invalidEndpoint");

    /* expect exception */
    this.exception.expect(HttpClientErrorException.class);

    this.restTemplate().getForEntity(numSeatsAvailableEndpoint, SeatAvailabilityResponse.class);

  }

}
