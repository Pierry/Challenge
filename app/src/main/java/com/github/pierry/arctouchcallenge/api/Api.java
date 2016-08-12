package com.github.pierry.arctouchcallenge.api;

import android.content.Context;
import com.github.pierry.arctouchcallenge.api.contracts.IApi;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class Api implements IApi {

  @RootContext Context context;

  private static final String URL = "https://api.appglu.com/v1/queries/";

  static final String FIND_ROUTES_BY_STOPNAME = URL + "findRoutesByStopName/run";
  static final String FIND_STOP_BY_ID = URL + "findStopsByRouteId/run";
  static final String FIND_DEPARTURES_BY_ID = URL + "findDeparturesByRouteId/run";

  static final String POST = "POST";

  private static final String USERNAME = "WKD4N7YMA1uiM8V";
  private static final String PASSOWRD = "DtdTtzMLQlA0hk2C1Yi5pLyVIlAQ68";
  private static final String HEADER = "X-AppGlu-Environment";
  private static final String HEADER_VALUE = "staging";

  @Override public Future<Response<JsonObject>> post(String url, JsonObject jsonObject) {
    Future<Response<JsonObject>> callback = Ion.with(context)
        .load(POST, url)
        .addHeader(HEADER, HEADER_VALUE)
        .basicAuthentication(USERNAME, PASSOWRD)
        .setJsonObjectBody(jsonObject)
        .asJsonObject()
        .withResponse();
    return callback;
  }
}
