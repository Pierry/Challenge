package com.github.pierry.arctouchcallenge.domain.contracts;

import android.support.v7.widget.RecyclerView;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
import com.google.gson.JsonObject;
import java.util.List;

public interface IRouteService {

  List<Route> deserializeRoutes(JsonObject result);

  void updateRoutes(RecyclerView recyclerView, RouteAdapter routeAdapter, List<Route> routes);
}
