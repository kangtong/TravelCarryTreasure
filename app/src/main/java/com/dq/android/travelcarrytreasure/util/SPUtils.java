package com.dq.android.travelcarrytreasure.util;

import android.content.Context;

public class SPUtils {

  public static final String COMMON = "common";

  /**
   * 获取公共数据
   */
  public static String getString(Context context, String key) {
    return context.getSharedPreferences(COMMON, Context.MODE_PRIVATE).getString(key, "");
  }

  public static boolean getBoolean(Context context, String key) {
    return context.getSharedPreferences(COMMON, Context.MODE_PRIVATE).getBoolean(key, false);
  }

  /**
   * 设置公共缓存数据
   */
  public static void setString(Context context, String key, String keyValue) {
    context.getSharedPreferences(COMMON, Context.MODE_PRIVATE)
        .edit()
        .putString(key, keyValue)
        .apply();
  }

  public static void setBoolean(Context context, String key, boolean keyValue) {
    context.getSharedPreferences(COMMON, Context.MODE_PRIVATE)
        .edit()
        .putBoolean(key, keyValue)
        .apply();
  }
}