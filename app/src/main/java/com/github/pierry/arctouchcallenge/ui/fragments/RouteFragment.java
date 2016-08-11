package com.github.pierry.arctouchcallenge.ui.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import com.github.pierry.arctouchcallenge.MainActivity;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.api.RouteApi;
import com.github.pierry.arctouchcallenge.api.contracts.IRouteApi;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.domain.contracts.IRouteService;
import com.github.pierry.arctouchcallenge.services.RouteService;
import com.github.pierry.arctouchcallenge.ui.adapters.RouteAdapter;
import com.github.pierry.arctouchcallenge.ui.common.LoaderHelper;
import com.github.pierry.fitloader.RotateLoading;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_route) public class RouteFragment extends Fragment {

  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;
  @ViewById SwipeRefreshLayout swipeRefreshLayout;

  @Bean(RouteService.class) IRouteService routeService;
  @Bean(RouteApi.class) IRouteApi routeApi;
  @Bean(RouteAdapter.class) RouteAdapter routeAdapter;
  @Bean(LoaderHelper.class) LoaderHelper loaderHelper;

  private List<Route> routes = new ArrayList<>();

  @AfterViews void init() {
    setHasOptionsMenu(true);
    loaderHelper.add(rotateLoading, body);
    loaderHelper.showLoader();
    recyclerViewConfig();
    routeAdapter.fragmentManagerInject(getActivity().getSupportFragmentManager());
    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        load("");
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }

  @UiThread void recyclerViewConfig() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    load("");
  }

  @Background public void load(String query) {
    routeApi.get(query, recyclerView, routeAdapter, loaderHelper);
    adapter();
  }

  @UiThread void adapter() {
    routeAdapter.addItems(routes);
    recyclerView.setAdapter(routeAdapter);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    menu.clear();
    inflater.inflate(R.menu.main_menu, menu);
    MenuItem item = menu.findItem(R.id.actionSearch);
    SearchView searchView =
        new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
    MenuItemCompat.setShowAsAction(item,
        MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
    MenuItemCompat.setActionView(item, searchView);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        loaderHelper.showLoader();
        load(query);
        return true;
      }

      @Override public boolean onQueryTextChange(String newText) {
        if (newText.length() < 3 && newText.length() > 0) {
          return false;
        }
        loaderHelper.showLoader();
        load(newText);
        return true;
      }
    });
    searchView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {

      }
    });
  }
}
