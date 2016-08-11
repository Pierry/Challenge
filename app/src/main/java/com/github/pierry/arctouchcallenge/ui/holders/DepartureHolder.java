package com.github.pierry.arctouchcallenge.ui.holders;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.domain.Departure;
import com.github.pierry.arctouchcallenge.domain.Stop;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.holder_departure) public class DepartureHolder extends RelativeLayout {

  @ViewById TextView name;

  private Context context;
  private FragmentManager fragmentManager;
  private Departure departure;

  public DepartureHolder(Context context) {
    super(context);
    this.context = context;
  }

  @UiThread public void fragmentManagerInject(FragmentManager fm) {
    this.fragmentManager = fm;
  }

  @UiThread public void bind(final Departure departure) {
    this.departure = departure;
    name.setText(departure.getTime());
  }
}

