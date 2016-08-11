package com.github.pierry.arctouchcallenge.ui.adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.ui.common.RecyclerViewAdapterBase;
import com.github.pierry.arctouchcallenge.ui.common.ViewWrapper;
import com.github.pierry.arctouchcallenge.ui.holders.StopHolder;
import com.github.pierry.arctouchcallenge.ui.holders.StopHolder_;
import java.util.List;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean public class StopAdapter extends RecyclerViewAdapterBase<Stop, StopHolder> {

  @RootContext Context context;

  private FragmentManager fragmentManager;

  public void addItems(List<Stop> Stops) {
    this.items = Stops;
  }

  public void fragmentManagerInject(FragmentManager fragmentManager) {
    this.fragmentManager = fragmentManager;
  }

  @Override protected StopHolder onCreateItemView(ViewGroup parent, int viewType) {
    return StopHolder_.build(context);
  }

  @Override public void onBindViewHolder(ViewWrapper<StopHolder> viewHolder, int position) {
    StopHolder view = viewHolder.getView();
    view.fragmentManagerInject(fragmentManager);
    final Stop Stop = items.get(position);

    view.bind(Stop);
  }
}

