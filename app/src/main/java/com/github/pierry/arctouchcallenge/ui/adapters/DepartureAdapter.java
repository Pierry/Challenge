package com.github.pierry.arctouchcallenge.ui.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.ui.common.RecyclerViewAdapterBase;
import com.github.pierry.arctouchcallenge.ui.common.ViewWrapper;
import com.github.pierry.arctouchcallenge.ui.holders.DepartureHolder;
import com.github.pierry.arctouchcallenge.ui.holders.DepartureHolder_;
import java.util.List;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class DepartureAdapter extends RecyclerViewAdapterBase<Departure, DepartureHolder> {

  @RootContext Context context;

  private FragmentManager fragmentManager;

  public void addItems(List<Departure> departures) {
    this.items = departures;
  }

  public void fragmentManagerInject(FragmentManager fragmentManager) {
    this.fragmentManager = fragmentManager;
  }

  @Override protected DepartureHolder onCreateItemView(ViewGroup parent, int viewType) {
    return DepartureHolder_.build(context);
  }

  @Override public void onBindViewHolder(ViewWrapper<DepartureHolder> viewHolder, int position) {
    DepartureHolder view = viewHolder.getView();
    view.fragmentManagerInject(fragmentManager);
    final Departure departure = items.get(position);

    view.bind(departure);
  }
}

