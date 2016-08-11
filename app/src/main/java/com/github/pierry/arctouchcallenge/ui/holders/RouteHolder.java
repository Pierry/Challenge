package com.github.pierry.arctouchcallenge.ui.holders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.pierry.arctouchcallenge.DetailsActivity_;
import com.github.pierry.arctouchcallenge.R;
import com.github.pierry.arctouchcallenge.common.DateHelper;
import com.github.pierry.arctouchcallenge.domain.Route;
import com.github.pierry.arctouchcallenge.ui.common.ExtrasAlias;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.holder_route) public class RouteHolder extends RelativeLayout {

  @ViewById TextView name;
  @ViewById TextView timestamp;
  @ViewById TextView shortname;
  @ViewById CardView cardView;

  private Context context;
  private FragmentManager fragmentManager;
  private Route route;

  public RouteHolder(Context context) {
    super(context);
    this.context = context;
  }

  @UiThread public void fragmentManagerInject(FragmentManager fm) {
    this.fragmentManager = fm;
  }

  @UiThread public void bind(final Route route) {
    this.route = route;
    name.setText(route.getLongName());
    String date = DateHelper.date(route.getLastModifiedDate());
    timestamp.setText(date);
    shortname.setText(route.getShortName());
    cardView.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ExtrasAlias.ROUTE, route);
        Intent details = new Intent(context, DetailsActivity_.class);
        details.putExtras(bundle);
        context.startActivity(details);
      }
    });
  }
}

