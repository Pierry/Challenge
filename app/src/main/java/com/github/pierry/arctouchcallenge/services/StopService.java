package com.github.pierry.arctouchcallenge.services;

import android.content.Context;
import com.activeandroid.ActiveAndroid;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.api.DepartureApi;
import com.github.pierry.arctouchcallenge.api.contracts.IDepartureApi;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopRepository;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopService;
import com.github.pierry.arctouchcallenge.repositories.StopRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean public class StopService implements IStopService {

  @Bean(StopRepository.class) IStopRepository stopRepository;
  @Bean(DepartureApi.class) IDepartureApi departureApi;

  @Override public List<Stop> deserializeStops(JsonObject result) {
    Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT,
        Modifier.STATIC).create();
    JsonArray json = result.getAsJsonArray("rows");
    TypeToken listType = new TypeToken<List<Stop>>() {
    };
    List<Stop> stops = (List<Stop>) gson.fromJson(json, listType.getType());
    return stops;
  }

  @Override @Background public void saveStops(Context context, List<Stop> stops, long routeId) {
    ActiveAndroid.beginTransaction();
    for (Stop s : stops) {
      Stop stop = stopRepository.getById(s.getStopId());
      if (stop == null) {
        stopRepository.create(s);
      }
    }
    ActiveAndroid.endTransaction();
    if (context instanceof DetailsActivity_) {
      DetailsActivity_ act = (DetailsActivity_) context;
      act.completed(stops);
    }
  }
}
