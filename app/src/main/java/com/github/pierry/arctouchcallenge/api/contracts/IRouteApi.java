package com.github.pierry.arctouchcallenge.api.contracts;

import android.support.v7.widget.RecyclerView;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
import com.github.pierry.arctouchcallenge.ui.common.LoaderHelper;

public interface IRouteApi {

  void get(String stopName, final RecyclerView recyclerView, final RouteAdapter routeAdapter,
      LoaderHelper loaderHelper);
}
