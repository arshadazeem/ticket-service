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

import com.myorg.ticketservice.response.FindAndHoldServiceResponse;

public class FindAndHoldSeatIntegrationTest extends TicketServiceIntegrationTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  /**
   * success test for findAndHold service
   */
  @Test
  public void givenValidInput_whenFindAndHoldServiceCalled_thenReturnValidSeatHoldId() {

    this.printMessage("FindAndHoldSeatIntegrationTests", "valid input");

    final URI findAndHoldServiceEndpoint = this.makeServiceUri(FIND_AND_HOLD_SERVICE_ENDPOINT);

    final ResponseEntity<FindAndHoldServiceResponse> responseEntity = this.restTemplate().postForEntity(
        findAndHoldServiceEndpoint,
        this.getFindAndHoldServiceRequest(),
        FindAndHoldServiceResponse.class);

    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    assertNotNull(responseEntity.getBody());

    this.printDashedLine();
    this.convertToJsonAndPrint(responseEntity.getBody());
    this.printDashedLine();

    assertTrue(responseEntity.getBody().getSeatHold().getSeatHoldId() > 0);

  }


  /**
   * test for 400 errors
   */
  @Test
  public void givenNullInput_whenFindAndHoldServiceCalled_thenReturnClientError() {

    this.printMessage("FindAndHoldSeatIntegrationTests", "null input");

    final URI findAndHoldServiceEndpoint = this.makeServiceUri(FIND_AND_HOLD_SERVICE_ENDPOINT);

    /* expect exception */
    this.exception.expect(HttpClientErrorException.class);

    final ResponseEntity<FindAndHoldServiceResponse> responseEntity =
        this.restTemplate().postForEntity(findAndHoldServiceEndpoint, null, FindAndHoldServiceResponse.class);

  }

  /**
   * test for 404 at invalid endpoint
   */
  @Test
  public void givenInValidEndpoint_whenFindAndHoldServiceCalled_thenReturnHttpClientErrorException() {

    this.printMessage("FindAndHoldSeatIntegrationTests", "Invalid endpoint");

    final URI findAndHoldServiceEndpoint = this.makeServiceUri("invalidEndpoint");

    /* expect exception */
    this.exception.expect(HttpClientErrorException.class);

    this.restTemplate().postForEntity(
        findAndHoldServiceEndpoint,
        this.getFindAndHoldServiceRequest(),
        FindAndHoldServiceResponse.class);
  }

}
