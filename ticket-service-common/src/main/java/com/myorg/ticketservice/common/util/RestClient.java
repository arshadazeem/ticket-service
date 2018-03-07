package com.myorg.ticketservice.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Wrapper class for spring's <code>RestTemplate</code>.
 * Can provide service specific connection settings
 */
@Component
public class RestClient {

  private final RestTemplate restTemplate;

  @Autowired
  public RestClient(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public RestTemplate getRestTemplate() {
    return this.restTemplate;
  }

}
