package com.dq.android.travelcarrytreasure.util;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by DQDana on 2017/4/21
 */

public class ViewUtils {

  /**
   * 是否滑到底部了
   * @param view : 目标 view
   * @return
   */
  public static boolean canChildScrollUp(View view) {
    if (android.os.Build.VERSION.SDK_INT < 14) {
      if (view instanceof AbsListView) {
        final AbsListView absListView = (AbsListView) view;
        return absListView.getChildCount() > 0
            && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
            .getTop() < absListView.getPaddingTop());
      } else {
        return view.getScrollY() > 0;
      }
    } else {
      return view.canScrollVertically(-1);
    }
  }
}
