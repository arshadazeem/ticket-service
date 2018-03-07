package com.myorg.ticketservice.integration.tests;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.myorg.ticketservice.common.config.AppConfig;
import com.myorg.ticketservice.common.util.ConfirmationNumberGenerator;
import com.myorg.ticketservice.common.util.DateUtil;
import com.myorg.ticketservice.common.util.RestClient;
import com.myorg.ticketservice.common.util.TicketUtil;
import com.myorg.ticketservice.controller.TicketController;
import com.myorg.ticketservice.repository.TicketRepositoryImpl;
import com.myorg.ticketservice.request.FindAndHoldServiceRequest;
import com.myorg.ticketservice.request.SeatReservationRequest;
import com.myorg.ticketservice.service.TicketServiceImpl;

/**
 * Base class for ticket service integration tests
 *
 * @author arshad.azeem
 *
 */
@ContextConfiguration(classes = {
    TicketController.class,
    TicketServiceImpl.class,
    TicketRepositoryImpl.class,
    TicketUtil.class,
    AppConfig.class,
    DateUtil.class,
    ConfirmationNumberGenerator.class,
    RestTemplate.class,
    RestClient.class}, loader = SpringBootContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class TicketServiceIntegrationTest {


  @Autowired
  private RestClient restClient;

  /* constants */
  public static final String TICKETS = "/tickets/";
  public static final String HOST = "http://localhost:";
  public static final String DEFAULT_PORT = "8080";
  public static final String SERVER_PORT = "server.port";
  public static final String AVAILABILITY_SERVICE_ENDPOINT = "/availability/count";
  public static final String FIND_AND_HOLD_SERVICE_ENDPOINT = "find-and-hold";
  public static final String RESERVATION_SERVICE_ENDPOINT = "reserve";
  public static final String CUSTOMER_EMAIL = "aazeem@test.com";
  public static final int SEAT_COUNT = 5;

  /**
   * returns rest client
   *
   * @return <code>RestClient</code>
   */
  protected RestClient getRestClient() {
    return this.restClient;
  }

  /**
   * returns spring Rest Template
   *
   * @return <code>RestTemplate</code>
   */
  protected RestTemplate restTemplate() {
    return this.getRestClient().getRestTemplate();
  }

  /**
   * Constructs a full URI to a ticket service using the given endpoint.
   *
   * @param endpoint endpoint
   * @return service uri
   */
  protected final URI makeServiceUri(final String endpoint) {

    String port = System.getProperty(SERVER_PORT);

    if (Strings.isNullOrEmpty(port)) {
      port = DEFAULT_PORT;
    }

    final String uri = HOST + port + TICKETS + endpoint;
    try {
      return new URI(uri);
    } catch (final URISyntaxException e) {
      // TODO - LOG
      throw new RuntimeException(endpoint, e);
    }
  }

  /**
   * converts object to json and print to output stream
   *
   * @param t
   */
  protected <T> void convertToJsonAndPrint(final T t) {
    final ObjectMapper mapper = new ObjectMapper();
    try {
      final String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(t);
      System.out.println("json response: \n" + json);
    } catch (final JsonProcessingException e) {
      System.out.println("Exception writing object as string " + e);
    }

  }

  protected FindAndHoldServiceRequest getFindAndHoldServiceRequest() {
    final FindAndHoldServiceRequest findAndHoldServiceRequest = new FindAndHoldServiceRequest();
    findAndHoldServiceRequest.setNumSeats(SEAT_COUNT);
    findAndHoldServiceRequest.setCustomerEmail(CUSTOMER_EMAIL);

    return findAndHoldServiceRequest;

  }

  protected SeatReservationRequest getReservationServiceRequest(final int seatHoldId) {
    final SeatReservationRequest seatReservationRequest = new SeatReservationRequest();
    seatReservationRequest.setSeatHoldId(seatHoldId);
    seatReservationRequest.setCustomerEmail(CUSTOMER_EMAIL);

    return seatReservationRequest;

  }

  protected void printMessage(final String testName, final String condition) {
    this.printDashedLine();
    System.out.println("Running " + testName + "for " + condition + "...");
  }

  protected void printDashedLine() {
    System.out.println("-------------------------------------------------------------");
  }

}
