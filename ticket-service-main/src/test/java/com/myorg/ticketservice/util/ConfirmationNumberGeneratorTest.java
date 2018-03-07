package com.myorg.ticketservice.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.myorg.ticketservice.common.util.ConfirmationNumberGenerator;

public class ConfirmationNumberGeneratorTest {

  private final ConfirmationNumberGenerator confirmationNumberGenerator = new ConfirmationNumberGenerator();

  @Test
  public void testGenerateConfirmationNumber() {
    final String confNumber = this.confirmationNumberGenerator.generateConfirmationNumber();

    assertNotNull(confNumber);
    assertTrue(confNumber.length() == 36);
    // TODO - assert regex
  }

}
