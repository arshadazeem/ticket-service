package com.myorg.ticketservice.integration.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URI;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myorg.ticketservice.response.FindAndHoldServiceResponse;
import com.myorg.ticketservice.response.SeatReservationResponse;

/**
 * End to End integration test that finds and holds best available seats,
 * and reserves them
 *
 * @author arshad.azeem
 *
 */
public class FindHoldAndReserveE2EIntegrationTest extends TicketServiceIntegrationTest {

  /**
   * test E2E success scenario
   */
  @Test
  public void givenValidInput_whenFindAndHoldAndReserveServicesCalled_thenReturnValidConfirmation() {

    this.printMessage("End to End Integration Test", "valid input");

    /* find and hold seats */
    final int seatHoldId = this.findAndHold();

    /* Reserve the seatHold */
    this.reserve(seatHoldId);

  }

  /**
   * find and hold seat
   * 
   * @return
   */
  private int findAndHold() {

    this.printMessage("findAndHoldSeatService", "E2E Integration test");

    final URI findAndHoldServiceEndpoint = this.makeServiceUri(FIND_AND_HOLD_SERVICE_ENDPOINT);

    final ResponseEntity<FindAndHoldServiceResponse> findAndHoldResponseEntity = this.restTemplate().postForEntity(
        findAndHoldServiceEndpoint,
        this.getFindAndHoldServiceRequest(),
        FindAndHoldServiceResponse.class);

    assertNotNull(findAndHoldResponseEntity);
    assertEquals(HttpStatus.OK, findAndHoldResponseEntity.getStatusCode());

    assertNotNull(findAndHoldResponseEntity.getBody());

    System.out.println("Response received from FindAndHoldService: " + findAndHoldResponseEntity.getBody());
    this.printDashedLine();
    this.convertToJsonAndPrint(findAndHoldResponseEntity.getBody());
    this.printDashedLine();

    final int seatHoldId = findAndHoldResponseEntity.getBody().getSeatHold().getSeatHoldId();

    assertTrue(seatHoldId > 0);
    return seatHoldId;
  }

  /**
   * reserve seat
   * 
   * @param seatHoldId
   */
  private void reserve(final int seatHoldId) {

    final String message = "Reserve Seats service with seatHoldId: " + seatHoldId;
    this.printMessage(message, "E2E Integration test");

    final URI reserveServiceEndpoint = this.makeServiceUri(RESERVATION_SERVICE_ENDPOINT);

    final ResponseEntity<SeatReservationResponse> responseEntity = this.restTemplate().postForEntity(
        reserveServiceEndpoint,
        this.getReservationServiceRequest(seatHoldId),
        SeatReservationResponse.class);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    assertNotNull(responseEntity.getBody());

    System.out.println("Response received from Reserve Service: " + responseEntity.getBody());
    this.printDashedLine();
    this.convertToJsonAndPrint(responseEntity.getBody());
    this.printDashedLine();

    assertTrue(responseEntity.getBody().getConfirmationNumber().length() == 36);
  }

}
