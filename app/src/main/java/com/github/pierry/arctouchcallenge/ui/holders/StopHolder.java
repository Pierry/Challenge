package com.github.pierry.arctouchcallenge.ui.holders;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.domain.Stop;
import com.github.pierry.simpletoast.SimpleToast;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.holder_stop) public class StopHolder extends RelativeLayout {

  @ViewById TextView name;
  @ViewById CardView cardView;

  private Context context;
  private FragmentManager fragmentManager;
  private Stop stop;

  public StopHolder(Context context) {
    super(context);
    this.context = context;
  }

  @Click void cardView() {
    Log.e("Click", "Click");
  }

  @UiThread public void fragmentManagerInject(FragmentManager fm) {
    this.fragmentManager = fm;
  }

  @UiThread public void bind(final Stop stop) {
    this.stop = stop;
    name.setText(stop.getName());
  }
}

