package com.github.pierry.arctouchcallenge.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.github.pierry.arctouchcallenge.ui.fragments.MapFragment_;
import com.github.pierry.arctouchcallenge.ui.fragments.RouteFragment_;

public class MainAdapter extends FragmentPagerAdapter {

  private String[] TITLES = { "Rotas", "Mapa" };
  private final int PAGE_COUNT = 2;

  public MainAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public int getCount() {
    return PAGE_COUNT;
  }

  @Override public CharSequence getPageTitle(int position) {
    return TITLES[position];
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return new RouteFragment_();
      case 1:
        return new MapFragment_();
    }
    return new RouteFragment_();
  }
}