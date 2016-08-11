package com.github.pierry.arctouchcallenge.domain.contracts;

import android.content.Context;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.google.gson.JsonObject;
import java.util.List;

public interface IStopService {

  List<Stop> deserializeStops(JsonObject result);

  void saveStops(Context context, List<Stop> stops, long routeId);

}
