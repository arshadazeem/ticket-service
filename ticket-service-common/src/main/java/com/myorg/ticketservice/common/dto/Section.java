package com.myorg.ticketservice.common.dto;

/**
 * Holds Section Information in a venue
 *
 * @author arshad.azeem
 *
 */
public class Section {

  private final String sectionId;
  private String sectionName;

  public Section(final String sectionId, final String sectionName) {
    this.sectionId = sectionId;
    this.sectionName = sectionName;
  }

  public String getSectionId() {
    return this.sectionId;
  }

  public String getSectionName() {
    return this.sectionName;
  }

  public void setSectionName(final String sectionName) {
    this.sectionName = sectionName;
  }

}
