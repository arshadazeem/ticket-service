package com.myorg.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * bootstraps spring boot application
 *
 */
@EnableAutoConfiguration
@ComponentScan({"com.myorg.ticketservice.*"})
@SpringBootApplication
public class Application {
  public static void main(final String[] args) {
    System.out.println("Spring boot app");
    SpringApplication.run(Application.class, args);
  }

  /**
   * create bean for <code>RestTemplate</code>
   * 
   * @return
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
