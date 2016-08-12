package com.github.pierry.arctouchcallenge;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.arctouchcallenge.api.DepartureApi;
import com.github.pierry.arctouchcallenge.api.RouteApi;
import com.github.pierry.arctouchcallenge.api.StopApi;
import com.github.pierry.arctouchcallenge.api.contracts.IDepartureApi;
import com.github.pierry.arctouchcallenge.api.contracts.IRouteApi;
import com.github.pierry.arctouchcallenge.api.contracts.IStopApi;
import com.github.pierry.arctouchcallenge.common.DateHelper;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopRepository;
import com.github.pierry.arctouchcallenge.domain.contracts.IStopService;
import com.github.pierry.arctouchcallenge.repositories.StopRepository;
import com.github.pierry.arctouchcallenge.services.StopService;
import com.github.pierry.arctouchcallenge.ui.adapters.StopAdapter;
import com.github.pierry.arctouchcallenge.ui.common.ExtrasAlias;
import com.github.pierry.arctouchcallenge.ui.common.LoaderHelper;
import com.github.pierry.arctouchcallenge.ui.common.NotifyHelper;
import com.github.pierry.arctouchcallenge.ui.fragments.TimetableFragment;
import com.github.pierry.fitloader.RotateLoading;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@OptionsMenu(R.menu.route_menu) @EActivity(R.layout.activity_details) public class DetailsActivity
    extends AppCompatActivity {

  @ViewById Toolbar toolbar;
  @ViewById RecyclerView recyclerView;
  @ViewById LinearLayout body;
  @ViewById RotateLoading rotateLoading;

  @Bean NotifyHelper notifyHelper;
  @Bean(RouteApi.class) IRouteApi routeApi;
  @Bean(StopApi.class) IStopApi stopApi;
  @Bean(DepartureApi.class) IDepartureApi departureApi;
  @Bean(StopService.class) IStopService stopService;
  @Bean(StopRepository.class) IStopRepository stopRepository;
  @Bean StopAdapter stopAdapter;
  @Bean LoaderHelper loaderHelper;

  private Route route;

  @AfterViews void init() {
    Bundle bundle = getIntent().getExtras();
    route = (Route) bundle.getSerializable(ExtrasAlias.ROUTE);
    if (route == null) {
      notifyHelper.error(getResources().getString(R.string.load_error));
      super.onBackPressed();
      return;
    }
    toolbar.setSubtitle(route.getLongName());
    toolbar.setTitle(DateHelper.date(route.getLastModifiedDate()));
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    loaderHelper.add(rotateLoading, body);
    loaderHelper.showLoader();
    recyclerViewConfig();
  }

  @UiThread void recyclerViewConfig() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    loadItems(route.getId());
  }

  @Background public void loadItems(long routeId) {
    stopApi.stopByRouteId(routeId);
    departureApi.departuresById(routeId);
  }

  @UiThread void adapter(List<Stop> stops) {
    stopAdapter.addItems(stops);
    recyclerView.setAdapter(stopAdapter);
  }

  @Background public void completed(List<Stop> stops) {
    loaderHelper.hideLoader();
    adapter(stops);
  }

  @OptionsItem(android.R.id.home) void homeTap() {
    super.onBackPressed();
  }

  @OptionsItem(R.id.timetableAction) void timetabelTap() {
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    DialogFragment newFragment = TimetableFragment.newInstance(route.getId());
    newFragment.show(getSupportFragmentManager(), "dialog");
  }
}
