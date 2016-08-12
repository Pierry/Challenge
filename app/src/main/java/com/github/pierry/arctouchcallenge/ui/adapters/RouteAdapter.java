package com.github.pierry.arctouchcallenge.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.ui.common.RecyclerViewAdapterBase;
import com.github.pierry.arctouchcallenge.ui.common.ViewWrapper;
import com.github.pierry.arctouchcallenge.ui.holders.RouteHolder;
import com.github.pierry.arctouchcallenge.ui.holders.RouteHolder_;
import java.util.List;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class RouteAdapter extends RecyclerViewAdapterBase<Route, RouteHolder> {

  @RootContext Context context;

  private FragmentManager fragmentManager;

  public void addItems(List<Route> routes) {
    this.items = routes;
  }

  public void fragmentManagerInject(FragmentManager fragmentManager) {
    this.fragmentManager = fragmentManager;
  }

  @Override protected RouteHolder onCreateItemView(ViewGroup parent, int viewType) {
    return RouteHolder_.build(context);
  }

  @Override public void onBindViewHolder(ViewWrapper<RouteHolder> viewHolder, int position) {
    RouteHolder view = viewHolder.getView();
    view.fragmentManagerInject(fragmentManager);
    final Route route = items.get(position);

    view.bind(route);
  }
}

