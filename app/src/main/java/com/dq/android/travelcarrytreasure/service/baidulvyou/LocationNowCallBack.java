package com.dq.android.travelcarrytreasure.service.baidulvyou;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationNowResponse;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * 作者：Create on 2017/5/3 09:24  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public abstract class LocationNowCallBack extends Callback<LocationNowResponse> {

  @Override public LocationNowResponse parseNetworkResponse(Response response, int id) throws Exception {
    String string = response.body().string();
    return JSON.parseObject(string, LocationNowResponse.class);
  }
}
