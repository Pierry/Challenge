package com.github.pierry.arctouchcallenge.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import com.github.pierry.arctouchcallenge.DetailsActivity;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.api.contracts.IApi;
import com.github.pierry.arctouchcallenge.api.contracts.IRouteApi;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.domain.contracts.IRouteService;
import com.github.pierry.arctouchcallenge.services.RouteService;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
import com.github.pierry.arctouchcallenge.ui.common.LoaderHelper;
import com.github.pierry.arctouchcallenge.ui.common.NotifyHelper;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class RouteApi implements IRouteApi {

  @Bean(Api.class) IApi api;
  @Bean(NotifyHelper.class) NotifyHelper notifyHelper;
  @Bean(RouteService.class) IRouteService routeService;

  @RootContext Context context;

  @Override
  public void get(String stopName, final RecyclerView recyclerView, final RouteAdapter routeAdapter,
      final LoaderHelper loaderHelper) {
    JsonObject stopJson = new JsonObject();
    stopJson.addProperty("stopName", "%" + stopName + "%");

    JsonObject json = new JsonObject();
    json.add("params", stopJson);

    api.post(Api.FIND_ROUTES_BY_STOPNAME, json)
        .setCallback(new FutureCallback<Response<JsonObject>>() {
          @Override public void onCompleted(Exception e, Response<JsonObject> result) {
            if (e != null) {
              notifyHelper.error(context.getResources().getString(R.string.null_response));
              return;
            }
            int statusCode = result.getHeaders().code();
            switch (statusCode) {
              case 200:
                List<Route> routes = routeService.deserializeRoutes(result.getResult());
                routeService.updateRoutes(recyclerView, routeAdapter, routes);
                loaderHelper.hideLoader();
                break;
            }
          }
        });
  }
}
