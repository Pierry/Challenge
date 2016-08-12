package com.github.pierry.arctouchcallenge.services;

import android.content.Context;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureService;
import com.github.pierry.arctouchcallenge.repositories.DepartureRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Modifier;
import java.util.List;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean public class DepartureService implements IDepartureService {

  @Bean(DepartureRepository.class) IDepartureRepository departureRepository;

  @Override public List<Departure> deserializeDepartures(JsonObject result) {
    Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT,
        Modifier.STATIC).create();
    JsonArray json = result.getAsJsonArray("rows");
    TypeToken listType = new TypeToken<List<Departure>>() {
    };
    List<Departure> departures = (List<Departure>) gson.fromJson(json, listType.getType());
    return departures;
  }

  @Override @Background public void saveDepartures(List<Departure> departures, long routeId) {
    for (Departure d : departures) {
      d.setRouteId((int) routeId);
      departureRepository.create(d);
    }
  }
}
