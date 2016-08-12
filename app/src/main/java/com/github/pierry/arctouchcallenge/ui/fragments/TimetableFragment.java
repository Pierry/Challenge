package com.github.pierry.arctouchcallenge.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import com.github.pierry.arctouchcallenge.repositories.DepartureRepository;
import com.github.pierry.arctouchcallenge.ui.adapters.DepartureAdapter;
import com.github.pierry.fitloader.RotateLoading;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_dialog) public class TimetableFragment extends DialogFragment {

  @ViewById Button weekday;
  @ViewById Button saturday;
  @ViewById Button sunday;
  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;

  @Bean(DepartureRepository.class) IDepartureRepository departureRepository;
  @Bean DepartureAdapter departureAdapter;

  private long routeId;

  public static TimetableFragment newInstance(long routeId) {
    TimetableFragment_ f = new TimetableFragment_();
    Bundle args = new Bundle();
    args.putLong("routeId", routeId);
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {
    getDialog().setTitle(getActivity().getResources().getString(R.string.title_dialog_fragment));
    routeId = getArguments().getLong("routeId");
    recyclerViewConfig();
  }

  @Click void weekday() {
    List<Departure> departures = departureRepository.getWeekdayByRouteId(routeId);
    adapter(departures);
  }

  @Click void saturday() {
    List<Departure> departures = departureRepository.getSaturdayByRouteId(routeId);
    adapter(departures);
  }

  @Click void sunday() {
    List<Departure> departures = departureRepository.getSundayByRouteId(routeId);
    adapter(departures);
  }

  @UiThread void recyclerViewConfig() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    loadItems();
  }

  @Background public void loadItems() {
    List<Departure> departures = departureRepository.getWeekdayByRouteId(routeId);
    adapter(departures);
  }

  @UiThread void adapter(List<Departure> departures) {
    departureAdapter.addItems(departures);
    recyclerView.setAdapter(departureAdapter);
  }
}