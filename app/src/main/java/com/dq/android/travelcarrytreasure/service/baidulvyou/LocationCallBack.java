package com.dq.android.travelcarrytreasure.service.baidulvyou;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationResponse;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Response;

/**
 * Created by DQDana on 2017/4/27
 */

public abstract class LocationCallBack extends Callback<LocationResponse> {

  @Override public LocationResponse parseNetworkResponse(Response response, int id)
      throws IOException {
    String string = response.body().string();
    return JSON.parseObject(string, LocationResponse.class);
  }
}