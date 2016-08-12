package com.github.pierry.arctouchcallenge.api;

import android.content.Context;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.api.contracts.IApi;
import com.github.pierry.arctouchcallenge.api.contracts.IDepartureApi;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureService;
import com.github.pierry.arctouchcallenge.services.DepartureService;
import com.github.pierry.arctouchcallenge.ui.common.NotifyHelper;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Response;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class DepartureApi implements IDepartureApi {

  @RootContext Context context;

  @Bean NotifyHelper notifyHelper;
  @Bean(Api.class) IApi api;
  @Bean(DepartureService.class) IDepartureService departureService;

  @Override public void departuresById(final long id) {
    JsonObject stopJson = new JsonObject();
    stopJson.addProperty("routeId", id);

    JsonObject json = new JsonObject();
    json.add("params", stopJson);

    api.post(Api.FIND_DEPARTURES_BY_ID, json)
        .setCallback(new FutureCallback<Response<JsonObject>>() {
          @Override public void onCompleted(Exception e, Response<JsonObject> result) {
            if (e != null) {
              notifyHelper.error(context.getResources().getString(R.string.null_response));
              return;
            }
            int statusCode = result.getHeaders().code();
            switch (statusCode) {
              case 200:
                List<Departure> departures =
                    departureService.deserializeDepartures(result.getResult());
                departureService.saveDepartures(departures, id);
                break;
            }
          }
        });
  }
}
