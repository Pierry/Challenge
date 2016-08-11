package com.github.pierry.arctouchcallenge.ui.holders;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.domain.Stop;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.holder_stop) public class StopHolder extends RelativeLayout {

  @ViewById TextView name;

  private Context context;
  private Stop stop;

  public StopHolder(Context context) {
    super(context);
    this.context = context;
  }

  @UiThread public void bind(final Stop stop) {
    this.stop = stop;
    name.setText(stop.getName());
  }
}

