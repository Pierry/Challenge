package com.github.pierry.arctouchcallenge.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "Departure") public class Departure extends Model {

  @SerializedName("id") @Column(name = "DepartureId") long departureId;
  @Column(name = "Calendar") String calendar;
  @Column(name = "Time") String time;
  @Column(name = "RouteId") transient int routeId;

  public Departure() {
    super();
  }

  public long getDepartureId() {
    return departureId;
  }

  public void setDepartureId(long departureId) {
    this.departureId = departureId;
  }

  public String getCalendar() {
    return calendar;
  }

  public void setCalendar(String calendar) {
    this.calendar = calendar;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public int getRouteId() {
    return routeId;
  }

  public void setRouteId(int routeId) {
    this.routeId = routeId;
  }
}
