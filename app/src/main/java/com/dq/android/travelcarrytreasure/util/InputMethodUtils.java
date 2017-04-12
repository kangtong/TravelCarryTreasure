package com.dq.android.travelcarrytreasure.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class InputMethodUtils {

  public static void hideInputMethod(Context context, View v) {
    if (context != null) {
      InputMethodManager inputMethodManager =
          (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      if (inputMethodManager.isActive()) {
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
      }
    }
  }

  public static void showInputMethod(Context context, View v) {
    if (context != null) {
      InputMethodManager inputMethodManager =
          (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
      if (inputMethodManager.isActive()) {
        inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED);
      }
    }
  }
}