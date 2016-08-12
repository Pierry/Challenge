package com.github.pierry.arctouchcallenge.services;

import android.support.v7.widget.RecyclerView;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import com.github.pierry.arctouchcallenge.domain.contracts.IRouteService;
import com.github.pierry.arctouchcallenge.repositories.DepartureRepository;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
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
import org.androidannotations.annotations.UiThread;

@EBean public class RouteService implements IRouteService {

  @Bean(DepartureRepository.class) IDepartureRepository departureRepository;

  @Override public List<Route> deserializeRoutes(JsonObject result) {
    Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT,
        Modifier.STATIC).create();
    JsonArray json = result.getAsJsonArray("rows");
    TypeToken listType = new TypeToken<List<Route>>() {
    };
    List<Route> routes = (List<Route>) gson.fromJson(json, listType.getType());
    return routes;
  }

  @UiThread @Background public void updateRoutes(RecyclerView recyclerView, RouteAdapter routeAdapter,
      List<Route> routes) {
    routeAdapter.addItems(routes);
    recyclerView.setAdapter(routeAdapter);
  }
}
