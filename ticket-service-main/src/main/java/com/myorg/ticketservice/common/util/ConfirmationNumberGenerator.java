package com.myorg.ticketservice.common.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Confirmation number generator, uses a uuid generator
 *
 * @author arshad.azeem
 *
 */
@Component
public class ConfirmationNumberGenerator {

  @Autowired
  public ConfirmationNumberGenerator() {
    super();
  }

  /**
   * very basic confirmation number generator
   * Using guid for the purpose of this exercise
   *
   * @return String confirmation number guid
   */
  public String generateConfirmationNumber() {
    return UUID.randomUUID().toString();
  }

}
