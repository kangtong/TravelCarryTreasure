package com.dq.android.travelcarrytreasure.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DQDana on 2017/4/15
 */

public class TimeUtils {

  /**
   * 从时间戳转为指定类型的字符串
   *
   * @param time : 时间戳 单位:秒
   * @param type : 指定 显示 类型:2017-04-01
   */
  public static String getDateFromTime(long time, String type) {
    return new SimpleDateFormat(type).format(new Date(time * 1000));
  }
}



