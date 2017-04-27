package com.dq.android.travelcarrytreasure.service.common;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.common.Weather;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * 作者：Create on 2017/4/26 22:50  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public abstract class WeatherCallBack extends Callback<Weather> {

  @Override public Weather parseNetworkResponse(Response response, int id) throws Exception {
    String string = response.body().string();
    return JSON.parseObject(string, Weather.class);
  }
}
