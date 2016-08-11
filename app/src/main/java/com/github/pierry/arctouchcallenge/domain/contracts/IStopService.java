package com.github.pierry.arctouchcallenge.domain.contracts;

import com.github.pierry.arctouchcallenge.domain.Stop;
import com.google.gson.JsonObject;
import java.util.List;

public interface IStopService {

  List<Stop> deserializeStops(JsonObject result);

  void saveStops(List<Stop> stops, long routeId);

}
