package com.github.pierry.arctouchcallenge.domain;

import java.io.Serializable;

public class Route implements Serializable {

  private long id;
  private String shortName;
  private String longName;
  private String lastModifiedDate;
  private long agencyId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getShortName() {
    return shortName;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public String getLongName() {
    return longName;
  }

  public void setLongName(String longName) {
    this.longName = longName;
  }

  public String getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(String lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public long getAgencyId() {
    return agencyId;
  }

  public void setAgencyId(long agencyId) {
    this.agencyId = agencyId;
  }
}
