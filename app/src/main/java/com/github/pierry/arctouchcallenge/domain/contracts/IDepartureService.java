package com.github.pierry.arctouchcallenge.domain.contracts;

import com.github.pierry.arctouchcallenge.domain.Departure;
import com.google.gson.JsonObject;
import java.util.List;

public interface IDepartureService {

  List<Departure> deserializeDepartures(JsonObject result);

  void saveDepartures(List<Departure> departures, long routeId);
}
