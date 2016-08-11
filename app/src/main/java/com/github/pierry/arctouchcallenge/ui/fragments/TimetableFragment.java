package com.github.pierry.arctouchcallenge.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.domain.contracts.IDepartureRepository;
import com.github.pierry.arctouchcallenge.repositories.DepartureRepository;
import com.github.pierry.fitloader.RotateLoading;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_dialog) public class TimetableFragment extends DialogFragment {

  @ViewById Button weekday;
  @ViewById Button saturday;
  @ViewById Button sunday;
  @ViewById RotateLoading rotateLoading;
  @ViewById RelativeLayout body;
  @ViewById RecyclerView recyclerView;

  @Bean(DepartureRepository.class) IDepartureRepository departureRepository;

  private int routeId;

  public static TimetableFragment newInstance(int routeId) {
    TimetableFragment_ f = new TimetableFragment_();
    Bundle args = new Bundle();
    args.("routeId", routeId);
    f.setArguments(args);

    return f;
  }

  @AfterViews void init() {

  }

  @Click void weekday(){

  }

  @Click void saturday(){

  }

  @Click void sunday(){

  }
}
