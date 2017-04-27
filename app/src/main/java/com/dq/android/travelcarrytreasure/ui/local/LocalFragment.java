package com.dq.android.travelcarrytreasure.ui.local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.Constant;
import com.dq.android.travelcarrytreasure.model.common.City;
import com.dq.android.travelcarrytreasure.model.common.FuzzyAddress;
import com.dq.android.travelcarrytreasure.model.common.Weather;
import com.dq.android.travelcarrytreasure.service.common.FuzzyAddressCallBack;
import com.dq.android.travelcarrytreasure.service.common.WeatherCallBack;
import com.dq.android.travelcarrytreasure.widget.ScrollableLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class LocalFragment extends BaseFragment implements View.OnClickListener {

  private static final String TAG = LocalFragment.class.getSimpleName();

  private ScrollableLayout mLayoutScroll;
  private RelativeLayout mLayoutCityDetails;
  private ImageView mIvCityBanner;
  private FrameLayout mLayoutSearch;
  private ImageView mIvSearch;
  private TextView mTvCover;
  private TextView mTvCity;
  private TextView mTvCityPinyin;
  private TextView mTvWeather;

  private String ip;
  private String cityCode;

  public static LocalFragment newInstance() {

    Bundle args = new Bundle();

    LocalFragment fragment = new LocalFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_local;
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {

    mLayoutScroll = (ScrollableLayout) view.findViewById(R.id.layout_scroll);
    mLayoutCityDetails = (RelativeLayout) view.findViewById(R.id.layout_city_details);
    mIvCityBanner = (ImageView) view.findViewById(R.id.iv_city_banner);
    mLayoutSearch = (FrameLayout) view.findViewById(R.id.layout_search);
    mIvSearch = (ImageView) view.findViewById(R.id.iv_search);
    mTvCover = (TextView) view.findViewById(R.id.tv_cover);
    mTvCity = (TextView) view.findViewById(R.id.tv_city);
    mTvCityPinyin = (TextView) view.findViewById(R.id.tv_city_pinyin);
    mTvWeather = (TextView) view.findViewById(R.id.tv_weather);

    mLayoutScroll.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
      @Override public void onScroll() {
        changeSearchViewAlpha();
      }
    });

    mLayoutSearch.getBackground().mutate().setAlpha(0);
    mLayoutSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ToastUtils.toast("跳转至搜索界面");
      }
    });

    mTvCity.setOnClickListener(this);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    onLoadData();
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_city:
        ChooseCityActivity.start(getContext());
        break;
    }
  }

  private void onLoadData() {
    // 1.通过高德获取城市名 + 天气
    getIP();
    // 2.glide加载大图
    // getBanner();
    // 3,其他数据加载
    // http://lvyou.baidu.com/destination/app/local?apiv=v2&sid=795ac511463263cf7ae3def3&y=40.001743&around=0&x=116.488043&format=app&m=8e66d8f81fdea5a65e83102dd354f290&LVCODE=5615def83ce898ef7bf0f1ddf4e8d731&T=1493274804

  }

  /* 改变透明度的操作 */
  private void changeSearchViewAlpha() {
    int[] location = new int[2];
    mLayoutCityDetails.getLocationOnScreen(location);

    int y = location[1] - 60; // 60 是状态栏的高度
    int absY = Math.abs(y);
    int height = mLayoutSearch.getHeight();

    if (absY >= height * 0.6) {
      mIvSearch.setImageResource(R.drawable.common_search_black);
      mTvCover.setTextColor(getResources().getColor(R.color.divider));
    } else {
      mIvSearch.setImageResource(R.drawable.common_search_white);
      mTvCover.setTextColor(getResources().getColor(R.color.white));
    }

    if (absY <= height) {
      mLayoutSearch.getBackground().mutate().setAlpha(
          (int) ((absY + 0.0) / height * 255));
    } else {
      mLayoutSearch.getBackground().mutate().setAlpha(255);
    }
  }

  /* 获取公网 ip */
  public void getIP() {
    String url = "http://pv.sohu.com/cityjson?ie=utf-8";
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new StringCallback() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "获取ip地址时，发生网络错误！！！");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(String response, int id) {
            Pattern pattern = Pattern
                .compile(
                    "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
            Matcher matcher = pattern.matcher(response);
            if (matcher.find()) {
              ip = matcher.group();
              getCityByIp(ip);
            }
          }
        });
  }

  /* 获取城市名 */
  private void getCityByIp(String ip) {
    String url = "http://restapi.amap.com/v3/ip?ip=" + ip + "&key=" + Constant.getInstance()
        .getGaodewebkey();
    Log.d(TAG, "getCityByIp: " + url);
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new FuzzyAddressCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "通过ip获取模糊的城市地址时，发生网络错误！！！");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(FuzzyAddress response, int id) {
            if (response.getStatus().equals("1")) { // 成功
              Log.d(TAG, "onResponse: " + "api返回数据成功~");
              // 展示数据
              mTvCity.setText(response.getCity());
              cityCode = response.getAdcode();
              // 城市名拼音
              List<City> list = Constant.getInstance().getCityList();
              for (int i = 0; i < list.size(); i++) {
                if (response.getCity().contains(list.get(i).getSname())) {
                  mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
                  break;
                }
              }
              getWeather(cityCode);
            } else {
              Log.d(TAG, "onResponse: " + response.getInfo());
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
          }
        });
  }

  /* 获取天气 */
  private void getWeather(String cityCode) {
    String url = "http://restapi.amap.com/v3/weather/weatherInfo?city="
        + cityCode
        + "&key="
        + Constant.getInstance().getGaodewebkey();
    Log.d(TAG, "getWeather: " + url);
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new WeatherCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "通过指定城市天气时，发生网络错误！！！");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(Weather response, int id) {
            if (response.getStatus().equals("1")) { // 成功
              Log.d(TAG, "onResponse: " + "api返回数据成功~");
              // 展示数据
              if (!response.getLives().isEmpty()) {
                mTvWeather.setText(response.getLives().get(0).getDescribe());
                Log.d(TAG, "onResponse: " + response.getLives().get(0).getDescribe());
              } else {
                Log.d(TAG, "onResponse: " + "api返回数据为空！！！");
              }
            } else {
              Log.d(TAG, "onResponse: " + response.getInfo());
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
          }
        });
  }

  public void getBanner() {
    String url = "";
    Glide.with(this).load(url).into(mIvCityBanner);
  }
}