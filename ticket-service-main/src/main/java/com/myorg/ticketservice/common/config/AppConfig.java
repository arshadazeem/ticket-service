package com.myorg.ticketservice.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Config to hold environment specific configuration properties
 *
 * @author arshad.azeem
 *
 */
@Component
@Configuration
public class AppConfig {

  /* Max Number of seats in a venue */
  @Value("${max.seats.in.venue: 50000}")
  private int maxSeatsInVenue;

  /* Max time after which the hold on seats will expire */
  @Value("${seats.hold.expiration.seconds: 300}")
  private long seatsHoldExpirationTimeInSeconds;

  /* Are tickets Available for sale */
  @Value("${tickets.available.for.sale: true}")
  private boolean ticketsAvailableForSale;

  /* tomcat port */
  @Value("${server.port:8080}")
  private int serverPort;

  public int getMaxSeatsInVenue() {
    return this.maxSeatsInVenue;
  }

  public long getSeatsHoldExpirationTimeInSeconds() {
    return this.seatsHoldExpirationTimeInSeconds;
  }

  public boolean isTicketsAvailableForSale() {
    return this.ticketsAvailableForSale;
  }

  public int getServerPort() {
    return this.serverPort;
  }

}
