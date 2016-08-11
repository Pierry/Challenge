package com.github.pierry.arctouchcallenge.ui.common;

import android.content.Context;
import com.github.pierry.simpletoast.SimpleToast;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

@EBean public class NotifyHelper {

  @RootContext Context context;

  @UiThread public void error(String msg) {
    SimpleToast.error(context, msg);
  }

  @UiThread public void ok(String msg) {
    SimpleToast.ok(context, msg);
  }

  @UiThread public void warning(String msg) {
    SimpleToast.warning(context, msg);
  }
}
