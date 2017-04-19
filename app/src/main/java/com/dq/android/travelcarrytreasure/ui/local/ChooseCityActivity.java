package com.dq.android.travelcarrytreasure.ui.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.model.baidulvyou.HotCityCallBack;
import com.dq.android.travelcarrytreasure.model.baidulvyou.HotCityResponse;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;

/**
 * Created by DQDana on 2017/4/19
 */

public class ChooseCityActivity extends BaseActivity {

  private static final String TAG = ChooseCityActivity.class.getSimpleName();
  private static final String KEY_HOT_CITY_RESPONSE = "key_hot_city_response";

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_choose_city); // 其实就是一个 recycleview + 侧边栏 +　顶部顶起来

    initViews();
    initListener();
    initData();
  }

  private void initViews() {
  }

  private void initListener() {
  }

  private void initData() {
  }

  /* 加载网络数据 */
  private void onLoadData() {
    String url =
        "http://lvyou.baidu.com/destination/app/topscene?is_sug=0&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=cdcefef956beedff16e48c2551e3a9e2&T=1492602633&locEnabled=YES&locType=GPS";
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new HotCityCallBack() {

          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "网络错误");
          }

          @Override public void onResponse(HotCityResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "api返回数据成功~");
              // 展示数据
              onShowData(response.getData().getList());
              // 保存至SP
              Gson gson = new Gson();
              String discover = gson.toJson(response);
              SPUtils.setString(ChooseCityActivity.this, KEY_HOT_CITY_RESPONSE, discover);
              Log.d(TAG, "onResponse: " + "成功保存response至SP~");
            } else {
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
          }
        });
  }

  /**
   * 对返回的数据 进行解析 +　展示
   */
  private void onShowData(List<HotCityResponse.DataBean.ListBean> data) {

  }
}
