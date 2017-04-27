package com.dq.android.travelcarrytreasure.service.baidulvyou;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.baidulvyou.TravellerNoteResponse;
import com.zhy.http.okhttp.callback.Callback;
import okhttp3.Response;

/**
 * Created by DQDana on 2017/4/21
 */

public abstract class TravellerNoteCallBack extends Callback<TravellerNoteResponse> {

  @Override public TravellerNoteResponse parseNetworkResponse(Response response, int id)
      throws Exception {
    String string = response.body().string();
    return JSON.parseObject(string, TravellerNoteResponse.class);
  }
}
