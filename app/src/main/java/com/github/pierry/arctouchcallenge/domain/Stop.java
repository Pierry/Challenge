package com.github.pierry.arctouchcallenge.domain;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.SerializedName;

@Table(name = "Stop") public class Stop extends Model {

  @SerializedName("id") @Column(name = "StopId") long stopId;
  @Column(name = "Name") String name;
  @Column(name = "Sequence") int sequence;
  @SerializedName("route_id") @Column(name = "RouteId") long routeId;

  public Stop() {
    super();
  }

  public long getStopId() {
    return stopId;
  }

  public void setStopId(long stopId) {
    this.stopId = stopId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public long getRouteId() {
    return routeId;
  }

  public void setRouteId(long routeId) {
    this.routeId = routeId;
  }
}
