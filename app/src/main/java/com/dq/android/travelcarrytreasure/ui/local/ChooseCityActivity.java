package com.dq.android.travelcarrytreasure.ui.local;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.model.Constant;
import com.dq.android.travelcarrytreasure.model.baidulvyou.HotCityResponse;
import com.dq.android.travelcarrytreasure.service.baidulvyou.HotCityCallBack;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.dq.android.travelcarrytreasure.widget.CustomToolBar;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;

/**
 * Created by DQDana on 2017/4/19
 */

public class ChooseCityActivity extends BaseActivity {

  private static final String TAG = ChooseCityActivity.class.getSimpleName();
  private static final String KEY_HOT_CITY_RESPONSE = "key_hot_city_response";
  private static final String KEY_LOCATION_CITY = "key_location_city";
  private static final String KEY_CITY = "key_city"; // 上次选择城市名

  private CustomToolBar mToolbar;
  private TextView mTvLocationCity;
  private RecyclerView mRecyclerView;
  private EasyRecyclerAdapter mAdapter;

  public static void start(Context context, String city) {
    Intent starter = new Intent(context, ChooseCityActivity.class);
    starter.putExtra(KEY_LOCATION_CITY, city);
    context.startActivity(starter);
  }

  public static void start(BaseActivity activity, String city, int requestCode) {
    Intent starter = new Intent(activity, ChooseCityActivity.class);
    starter.putExtra(KEY_LOCATION_CITY, city);
    activity.startActivityForResult(starter, requestCode);
  }

  public static void start(BaseActivity activity, int requestCode) {
    Intent starter = new Intent(activity, ChooseCityActivity.class);
    activity.startActivityForResult(starter, requestCode);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_choose_city); // 其实就是一个 RecycleView + 侧边栏 +　顶部顶起来

    initViews();
    initListener();
    initData();
  }

  private void initViews() {
    mToolbar = (CustomToolBar) findViewById(R.id.toolbar);
    mTvLocationCity = (TextView) findViewById(R.id.tv_location_city);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycle_hot_city);
  }

  private void initListener() {
    mToolbar.setOnToolBarListener(new CustomToolBar.onToolBarListener() {
      @Override public void onBackListener() {
        onBackPressed();
      }

      @Override public void onActionListener() {
      }
    });

    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    mRecyclerView.setHasFixedSize(true);
    mAdapter = new EasyRecyclerAdapter(this, HotCityResponse.DataBean.ListBean.class,
        HotCityViewHolder.class);
    mAdapter.setOnClickListener(new EasyViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int i, View view) {
        Intent intent = new Intent();
        HotCityResponse.DataBean.ListBean bean = (HotCityResponse.DataBean.ListBean) mAdapter.getItems().get(i);
        intent.putExtra("city", bean.getSname());
        intent.putExtra("cityCode", bean.getSid());
        intent.putExtra("x", bean.getMap_x());
        intent.putExtra("y", bean.getMap_y());
        setResult(RESULT_OK, intent);
        finish();
      }
    });
    mRecyclerView.setAdapter(mAdapter);
  }

  private void initData() {
    if (getIntent().getStringExtra(KEY_LOCATION_CITY) == null) {
      // SP 存储的值
      String json_city = SPUtils.getString(this, KEY_CITY);
      if (!json_city.isEmpty()) {
        mTvLocationCity.setText(json_city);
      }
    } else {
      // 传过来的值
      mTvLocationCity.setText(getIntent().getStringExtra(KEY_LOCATION_CITY));
    }

    String json = SPUtils.getString(this, KEY_HOT_CITY_RESPONSE);
    if (!json.isEmpty()) {
      HotCityResponse response = JSON.parseObject(json, HotCityResponse.class);
      onShowData(response.getData().getList());
    }
    onLoadData();
  }

  /* 加载网络数据 */
  private void onLoadData() {
    String url =
        "http://lvyou.baidu.com/destination/app/topscene?"
            + Constant.getInstance().getBaidulvyoukey();
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new HotCityCallBack() {

          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "选择城市界面,网络错误");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(HotCityResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "选择城市界面,api返回数据成功~");
              // 展示数据
              onShowData(response.getData().getList());
              // 保存至SP
              String discover = JSON.toJSONString(response);
              SPUtils.setString(ChooseCityActivity.this, KEY_HOT_CITY_RESPONSE, discover);
            } else {
              Log.d(TAG, "onResponse: " + "选择城市界面,api返回数据失败!!!");
            }
          }
        });
  }

  /**
   * 对返回的数据 进行解析 +　展示
   */
  private void onShowData(List<HotCityResponse.DataBean.ListBean> data) {
    mAdapter.addAll(data);
  }
}
