package com.github.pierry.arctouchcallenge;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import com.astuetz.PagerSlidingTabStrip;
import com.github.pierry.arctouchcallenge.ui.adapters.MainAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main) public class MainActivity extends AppCompatActivity
    implements ViewPager.OnPageChangeListener {

  @ViewById PagerSlidingTabStrip tabs;
  @ViewById ViewPager pager;
  @ViewById Toolbar toolbar;

  private MainAdapter mainAdapter;

  @AfterViews void init() {
    toolbar.setTitle(R.string.app_name);
    setSupportActionBar(toolbar);
    SystemBarTintManager tintManager = new SystemBarTintManager(this);
    tintManager.setStatusBarTintEnabled(true);
    mainAdapter = new MainAdapter(getSupportFragmentManager());
    pager.setAdapter(mainAdapter);
    tabs.setViewPager(pager);
    tabs.setTextColorResource(R.color.icons);
    tabs.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    tintManager.setTintColor(getResources().getColor(R.color.colorPrimary));
    tabs.setDividerColor(android.R.color.transparent);
    final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
        getResources().getDisplayMetrics());
    pager.setPageMargin(pageMargin);
    pager.setCurrentItem(0);
    tabs.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    switch (position) {
      case 0:
        getSupportActionBar().setTitle(getResources().getString(R.string.routes_title));
        break;
      case 1:
        getSupportActionBar().setTitle(getResources().getString(R.string.map_title));
        break;
    }
  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }
}
