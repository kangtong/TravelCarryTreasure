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

  /**
   * 获取 Boolean 值
   */
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

  /**
   * 设置 Boolean 值
   */
  public static void setBoolean(Context context, String key, boolean keyValue) {
    context.getSharedPreferences(COMMON, Context.MODE_PRIVATE)
        .edit()
        .putBoolean(key, keyValue)
        .apply();
  }

  /**
   * 删除公共缓存数据
   */
  public static void removeString(Context context, String key) {
    context.getSharedPreferences(COMMON, Context.MODE_PRIVATE)
        .edit()
        .remove(key)
        .apply();
  }
}