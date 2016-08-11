package com.github.pierry.arctouchcallenge.api.contracts;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.ion.Response;

public interface IApi {

  Future<Response<JsonObject>> post(String url, JsonObject jsonObject);
}
