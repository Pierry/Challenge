package com.github.pierry.arctouchcallenge.services;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.activeandroid.ActiveAndroid;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.api.RouteApi;
import com.github.pierry.arctouchcallenge.api.StopApi;
import com.github.pierry.arctouchcallenge.api.contracts.IRouteApi;
import com.github.pierry.arctouchcallenge.api.contracts.IStopApi;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import com.github.pierry.arctouchcallenge.domain.contracts.IRouteService;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopRepository;
import com.github.pierry.arctouchcallenge.repositories.DepartureRepository;
import com.github.pierry.arctouchcallenge.repositories.StopRepository;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

@EBean public class RouteService implements IRouteService {

  @Bean(DepartureRepository.class) IDepartureRepository departureRepository;

  private Gson gson = new Gson();

  @Override public List<Route> deserializeRoutes(JsonObject result) {
    JsonArray json = result.getAsJsonArray("rows");
    TypeToken listType = new TypeToken<List<Route>>() {
    };
    List<Route> routes = (List<Route>) gson.fromJson(json, listType.getType());
    return routes;
  }

  @UiThread public void updateRoutes(RecyclerView recyclerView, RouteAdapter routeAdapter,
      List<Route> routes) {
    routeAdapter.addItems(routes);
    recyclerView.setAdapter(routeAdapter);
  }
}
