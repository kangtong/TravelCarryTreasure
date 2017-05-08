package com.dq.android.travelcarrytreasure.service.wyt;

import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.model.wyt.RegisterResponse;
import com.zhy.http.okhttp.callback.Callback;
import java.io.IOException;
import okhttp3.Response;

/**
 * 作者：Create on 2017/5/8 22:49  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public abstract class RegisterCallBack extends Callback<RegisterResponse> {

  @Override public RegisterResponse parseNetworkResponse(Response response, int id)
      throws IOException {
    String string = response.body().string();
    return JSON.parseObject(string, RegisterResponse.class);
  }
}
