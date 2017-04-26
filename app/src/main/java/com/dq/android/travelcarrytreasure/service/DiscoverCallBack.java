package com.dq.android.travelcarrytreasure.service;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by DQDana on 2017/4/15
 */
public abstract class DiscoverCallBack extends Callback<DiscoverResponse> {

  @Override public DiscoverResponse parseNetworkResponse(Response response, int id)
      throws IOException {
    String string = response.body().string();
    return JSON.parseObject(string, DiscoverResponse.class);
  }
}