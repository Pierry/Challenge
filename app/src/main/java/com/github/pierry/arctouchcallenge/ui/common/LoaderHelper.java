package com.github.pierry.arctouchcallenge.ui.common;

import android.view.View;
import android.view.ViewGroup;
import com.github.pierry.fitloader.RotateLoading;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;

@EBean public class LoaderHelper {

  private RotateLoading rotateLoading;
  private ViewGroup body;

  @UiThread public void showLoader() {
    rotateLoading.start(false);
    rotateLoading.setVisibility(View.VISIBLE);
    body.setVisibility(View.GONE);
  }

  @UiThread public void hideLoader() {
    rotateLoading.stop();
    rotateLoading.setVisibility(View.GONE);
    body.setVisibility(View.VISIBLE);
  }

  @UiThread public void add(RotateLoading rotateLoading, ViewGroup body) {
    this.rotateLoading = rotateLoading;
    this.body = body;
  }
}
