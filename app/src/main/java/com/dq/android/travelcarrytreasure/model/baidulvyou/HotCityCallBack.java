package com.dq.android.travelcarrytreasure.model.baidulvyou;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * Created by DQDana on 2017/4/19
 */

public abstract class HotCityCallBack extends Callback<HotCityResponse> {

  @Override public HotCityResponse parseNetworkResponse(Response response, int id)
      throws Exception {
    String string = response.body().string();
    return JSON.parseObject(string, HotCityResponse.class);
  }
}

