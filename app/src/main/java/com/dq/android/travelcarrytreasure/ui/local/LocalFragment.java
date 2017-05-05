package com.dq.android.travelcarrytreasure.ui.local;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.Constant;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationNowResponse;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationResponse;
import com.dq.android.travelcarrytreasure.model.common.City;
import com.dq.android.travelcarrytreasure.model.common.FuzzyAddress;
import com.dq.android.travelcarrytreasure.model.common.Weather;
import com.dq.android.travelcarrytreasure.service.baidulvyou.LocationCallBack;
import com.dq.android.travelcarrytreasure.service.baidulvyou.LocationNowCallBack;
import com.dq.android.travelcarrytreasure.service.common.FuzzyAddressCallBack;
import com.dq.android.travelcarrytreasure.service.common.WeatherCallBack;
import com.dq.android.travelcarrytreasure.util.NetworkUtil;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class LocalFragment extends BaseFragment implements View.OnClickListener {

  private static final String TAG = LocalFragment.class.getSimpleName();

  private static final String KEY_LOCATION_RESPONSE = "key_location_response";
  private static final String KEY_LOCATION_LIVE_RESPONSE = "key_location_live_response";
  private static final String KEY_LATITUDE = "key_latitude"; // 维度
  private static final String KEY_LONGITUDE = "key_longitude"; // 经度
  private static final String KEY_CITY = "key_city"; // 城市名
  private static final String KEY_CITY_PINYIN = "key_city_pinyin"; // 城市拼音

  private ScrollView mLayoutScroll;

  /* toolbar */
  private RelativeLayout mLayoutCityDetails;
  private ImageView mIvCityBanner;
  private FrameLayout mLayoutSearch;
  private ImageView mIvSearch;
  private View mViewDivider;
  private TextView mTvCover;

  /* 定位+天气 */
  private TextView mTvCity;
  private TextView mTvCityPinyin;
  private TextView mTvWeather;

  /* 推荐行程 */
  private ImageView mImgUser;
  private TextView mTvTitleSub;
  private ImageView mImgStroke_1, mImgStroke_2, mImgStroke_3, mImgStroke_4, mImgStroke_5;
  private TextView mTvStroke_1, mTvStroke_2, mTvStroke_3, mTvStroke_4, mTvStroke_5;
  private TextView[] mTvSteps;
  private ImageView[] mImgSteps;

  /* 附近推荐 */
  private LinearLayout mLayoutNearby;
  private TextView mTvTitleSubNearby;
  private RecyclerView mRecycleNearby;
  private EasyRecyclerAdapter mAdapterNearby;

  /* 当地此刻 */
  private LinearLayout mLayoutLive;
  private TextView mTvTitleLive;
  private TextView mTvTitleSubLive;
  private RecyclerView mRecycleLive;
  private EasyRecyclerAdapter mAdapterLive;

  private String ip;
  private String cityCode;
  private Double[] LatitudeAndLongitude = new Double[] {0.0, 0.0};

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

    mLayoutScroll = (ScrollView) view.findViewById(R.id.layout_scroll);
    /* toolbar */
    mLayoutCityDetails = (RelativeLayout) view.findViewById(R.id.layout_city_details);
    mIvCityBanner = (ImageView) view.findViewById(R.id.iv_city_banner);
    mLayoutSearch = (FrameLayout) view.findViewById(R.id.layout_search);
    mIvSearch = (ImageView) view.findViewById(R.id.iv_search);
    mViewDivider = view.findViewById(R.id.view_divider);
    mTvCover = (TextView) view.findViewById(R.id.tv_cover);
    /* 定位 + 天气 */
    mTvCity = (TextView) view.findViewById(R.id.tv_city);
    mTvCityPinyin = (TextView) view.findViewById(R.id.tv_city_pinyin);
    mTvWeather = (TextView) view.findViewById(R.id.tv_weather);
    /* 推荐行程 */
    mImgUser = (ImageView) view.findViewById(R.id.img_user);
    mTvTitleSub = (TextView) view.findViewById(R.id.tv_title_sub);
    /* 行程的5个点位置 */
    mImgStroke_1 = (ImageView) view.findViewById(R.id.img_stroke_1);
    mImgStroke_2 = (ImageView) view.findViewById(R.id.img_stroke_2);
    mImgStroke_3 = (ImageView) view.findViewById(R.id.img_stroke_3);
    mImgStroke_4 = (ImageView) view.findViewById(R.id.img_stroke_4);
    mImgStroke_5 = (ImageView) view.findViewById(R.id.img_stroke_5);
    mImgSteps = new ImageView[] {mImgStroke_1, mImgStroke_2, mImgStroke_3, mImgStroke_4, mImgStroke_5};
    mTvStroke_1 = (TextView) view.findViewById(R.id.tv_stroke_1);
    mTvStroke_2 = (TextView) view.findViewById(R.id.tv_stroke_2);
    mTvStroke_3 = (TextView) view.findViewById(R.id.tv_stroke_3);
    mTvStroke_4 = (TextView) view.findViewById(R.id.tv_stroke_4);
    mTvStroke_5 = (TextView) view.findViewById(R.id.tv_stroke_5);
    mTvSteps = new TextView[] {mTvStroke_1, mTvStroke_2, mTvStroke_3, mTvStroke_4, mTvStroke_5};
    /* 附近推荐 */
    mLayoutNearby = (LinearLayout) view.findViewById(R.id.ll_nearby);
    mTvTitleSubNearby = (TextView) view.findViewById(R.id.tv_title_sub_2);
    mRecycleNearby = (RecyclerView) view.findViewById(R.id.recycle_nearby);
    /* 当地此刻 */
    mLayoutLive = (LinearLayout) view.findViewById(R.id.ll_location_now);
    mTvTitleLive = (TextView) view.findViewById(R.id.tv_title_3);
    mTvTitleSubLive = (TextView) view.findViewById(R.id.tv_title_sub_3);
    mRecycleLive = (RecyclerView) view.findViewById(R.id.recycle_location_now);

    // 滑动监听, 改变透明度
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      mLayoutScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
        @Override public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
          changeSearchViewAlpha();
        }
      });
    }

    // 搜索栏处理
    mViewDivider.getBackground().mutate().setAlpha(0);
    mLayoutSearch.getBackground().mutate().setAlpha(0);
    mLayoutSearch.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ToastUtils.toast("跳转至搜索界面");
      }
    });
    // 城市选择页跳转
    mTvCity.setOnClickListener(this);

    // 附近推荐相关
    final LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
    mLayoutManager.setSmoothScrollbarEnabled(true);
    mLayoutManager.setAutoMeasureEnabled(true);
    mRecycleNearby.setLayoutManager(mLayoutManager);
    mRecycleNearby.setHasFixedSize(true);
    mRecycleNearby.setNestedScrollingEnabled(false);
    mRecycleNearby.setItemAnimator(new DefaultItemAnimator());
    mAdapterNearby =
        new EasyRecyclerAdapter(getContext(), LocationResponse.DataBean.ModListBean.ListBean.class, LocalViewHolder.class);
    mAdapterNearby.setOnClickListener(new EasyViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int i, View view) {
        ToastUtils.toast("点击了第" + i + "个item~");
      }
    });
    mRecycleNearby.setAdapter(mAdapterNearby);

    // 当地此刻相关
    final GridLayoutManager mLayoutManager_2 = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
    mLayoutManager_2.setSmoothScrollbarEnabled(true);
    mLayoutManager_2.setAutoMeasureEnabled(true);
    mRecycleLive.setLayoutManager(mLayoutManager_2);
    mRecycleLive.setHasFixedSize(true);
    mRecycleLive.setNestedScrollingEnabled(false);
    mRecycleLive.setItemAnimator(new DefaultItemAnimator());
    mAdapterLive =
        new EasyRecyclerAdapter(getContext(), LocationNowResponse.DataBean.ListBean.class, LocalLiveViewHolder.class);
    mAdapterLive.setOnClickListener(new EasyViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int i, View view) {
        ToastUtils.toast("点击了第" + i + "个item~");
      }
    });
    mRecycleLive.setAdapter(mAdapterLive);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // 1, 先加载本地
    if (!onLoadDataWithSP()) { // 如果本地无缓存数据，再使用网络请求
      if (NetworkUtil.isNetworkAvailable()) {
        onLoadData(); // 2,网络请求
      } else {
        ToastUtils.toast("本地网络检查错误!!!");
      }
    }
  }

  private boolean onLoadDataWithSP() {
    boolean result = false;
    // 大头数据
    String json = SPUtils.getString(getContext(), KEY_LOCATION_RESPONSE);
    if (!json.isEmpty()) {
      LocationResponse response = JSON.parseObject(json, LocationResponse.class);
      onShowData(response.getData());
      result = true;
    }
    // 城市名天气
    String json_city = SPUtils.getString(getContext(), KEY_CITY);
    if (!json_city.isEmpty()) {
      mTvCity.setText(json_city);
      result = true;
    }
    String json_city_pinyin = SPUtils.getString(getContext(), KEY_CITY_PINYIN);
    if (!json_city_pinyin.isEmpty()) {
      mTvCityPinyin.setText(json_city_pinyin);
      result = true;
    }
    // 经度
    String json_longitude = SPUtils.getString(getContext(), KEY_LONGITUDE);
    if (!json_longitude.isEmpty()) {
      LatitudeAndLongitude[0] = Double.valueOf(json_longitude);
      result = true;
    }
    // 纬度
    String json_latitude = SPUtils.getString(getContext(), KEY_LATITUDE);
    if (!json_latitude.isEmpty()) {
      LatitudeAndLongitude[1] = Double.valueOf(json_latitude);
      result = true;
    }
    // 当地此刻 Live 的数据展示
    String json_live = SPUtils.getString(getContext(), KEY_LOCATION_LIVE_RESPONSE);
    if (!json_live.isEmpty()) {
      LocationNowResponse response = JSON.parseObject(json_live, LocationNowResponse.class);
      onShowLive(response.getData());
      result = true;
    }
    return result;
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_city:
        ChooseCityActivity.start((BaseActivity) getContext(), mTvCity.getText().toString(), 1006);
        break;
    }
  }

  private void onLoadData() {
    // 1.通过高德获取城市名 + 天气, 之后的东西，因为相互关联，所以依次调用
    getIP();
    // 2.当地此刻的数据，加载显示
    getLocationLive(null);
    // 3,其他数据加载
    // getOtherData();
  }

  /* 展示数据 */
  private void onShowData(LocationResponse.DataBean data) {
    // 先把天气给填进去
    mTvWeather.setText(data.getScene_info().getInfo().getWeather().getDescribe());
    // 加载 Banner
    Glide.with(LocalFragment.this)
        .load(data.getScene_info().getInfo().getPic_url())
        .placeholder(R.drawable.bg_default_place_holder)
        .thumbnail(0.1f)
        .into(mIvCityBanner);
    // 更新推荐行程
    Glide.with(LocalFragment.this) // 头像
        .load(data.getMod_list().get(0).getAvatar())
        .bitmapTransform(new CropCircleTransformation(getContext()))
        .into(mImgUser);
    mTvTitleSub.setText(data.getMod_list().get(0).getSubtitle()); // 副标题
    updateTravelStep(data.getMod_list().get(0).getList()); // 更新5个步骤
    // 附近推荐 /* 这里有可能是空的，比如北京就没有 */
    if (data.getMod_list().size() == 1) { // 只有推荐行程， 没有附近推荐
      mLayoutNearby.setVisibility(View.GONE);
      return;
    }
    mTvTitleSubNearby.setText(data.getMod_list().get(1).getSubtitle());
    mAdapterNearby.addAll(data.getMod_list().get(1).getList());
  }

  /* 更新推荐行程 */
  private void updateTravelStep(List<LocationResponse.DataBean.ModListBean.ListBean> list) {
    // 1，先判断一共，有几个景点
    int sum = list.size();
    for (int i = 0; i < list.size(); i++) {
      if (i >= 5) {
        break;
      }
      mTvSteps[i].setText(list.get(i).getName());
      mImgSteps[i].setVisibility(View.VISIBLE);
    }
    for (int i = 4; i > sum - 1; i--) { // 没有的设置透明度
      mTvSteps[i].setText("");
      mImgSteps[i].setVisibility(View.INVISIBLE);
    }
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
      mViewDivider.getBackground().mutate().setAlpha(
          (int) ((absY + 0.0) / height * 255));
    } else {
      mLayoutSearch.getBackground().mutate().setAlpha(255);
      mViewDivider.getBackground().mutate().setAlpha(255);
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
              LatitudeAndLongitude = response.getCenterPoint();
              Log.d(TAG, "onResponse: "
                  + "经纬度为: "
                  + LatitudeAndLongitude[0]
                  + ":"
                  + LatitudeAndLongitude[1]);

              // 城市名拼音
              List<City> list = Constant.getInstance().getCityList();
              for (int i = 0; i < list.size(); i++) {
                if (response.getCity().contains(list.get(i).getSname())) {
                  mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
                  break;
                }
              }
              // 保存至SP : 经纬度 + 城市名
              SPUtils.setString(getContext(), KEY_LATITUDE, LatitudeAndLongitude[1] + "");
              SPUtils.setString(getContext(), KEY_LONGITUDE, LatitudeAndLongitude[0] + "");
              SPUtils.setString(getContext(), KEY_CITY, mTvCity.getText().toString());
              SPUtils.setString(getContext(), KEY_CITY_PINYIN, mTvCityPinyin.getText().toString());
              // getWeather(cityCode); // 获取天气
              getOtherData(null); // 获取其他数据
            } else {
              Log.d(TAG, "onResponse: " + response.getInfo());
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
          }
        });
  }

  /* 获取天气 */
  private void getWeather(String cityCode) {
    // TODO: 2017/4/27 dengqi: 这个天气不准,将来换成高德地图的
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

  /* 获取其他数据: 这个数据很多, 小心处理, fastjson 经常报错 */
  public void getOtherData(String url) {
    if (url == null) {
      url = // 很多东西, 慢慢分析, 拆解
          "http://lvyou.baidu.com/destination/app/local?apiv=v2&around=0"
              + "&sid=9bb8ee381df41344144463f5" // 城市的id 不知道是怎么拿到的，现在这个写死是西安的
              + "&y=" + LatitudeAndLongitude[1]
              // + "40.001743" // 维度
              + "&x=" + LatitudeAndLongitude[0]
              // + "116.488043" // 经度
              + "&format=app&m=8e66d8f81fdea5a65e83102dd354f290&"
              + Constant.getInstance().getBaidulvyoukey();
    }
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new LocationCallBack() {
          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "当地界面api请求,发生网络错误");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(LocationResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "当地界面网络请求,api返回数据成功~");
              // 展示数据
              onShowData(response.getData());
              // 保存至SP
              String discover = JSON.toJSONString(response);
              SPUtils.setString(getContext(), KEY_LOCATION_RESPONSE, discover);
            } else {
              Log.d(TAG, "onResponse: " + "当地界面网络请求,api返回数据失败!!!");
            }
          }
        });
  }

  public void getLocationLive(String sid) {
    String url;
    if (sid == null) {
      url = // 这个返回的东西，相对清晰简单
          "http://lvyou.baidu.com/destination/app/event/live?picture=1&apiv=v2&rn=20&d=android"
              + "&sid="
              + "9bb8ee381df41344144463f5"
              + "&" + Constant.getInstance().getBaidulvyoukey();
    } else {
      url =
          "http://lvyou.baidu.com/destination/app/event/live?picture=1&apiv=v2&rn=20&d=android"
              + "&sid="
              + sid
              + "&" + Constant.getInstance().getBaidulvyoukey();
    }
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new LocationNowCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "当地界面 Live 请求,发生网络错误");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(LocationNowResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "当地界面 Live 请求,api返回数据成功~");
              // 展示数据
              onShowLive(response.getData());
              // 保存至SP
              String discover = JSON.toJSONString(response);
              SPUtils.setString(getContext(), KEY_LOCATION_LIVE_RESPONSE, discover);
            } else {
              Log.d(TAG, "onResponse: " + "当地界面 Live 请求,api返回数据失败!!!");
            }
          }
        });
  }

  /* 展示 当地此刻  */
  private void onShowLive(LocationNowResponse.DataBean data) {
    mAdapterLive.addAll(data.getList());
    mTvTitleLive.setText(data.getSname() + "·" + "此刻");
    mTvTitleSubLive.setText(data.getCount() + "人来过");
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1006 && resultCode == Activity.RESULT_OK) {
      String city = data.getStringExtra("city");
      String sid = data.getStringExtra("cityCode");
      double x = data.getDoubleExtra("x", 0.0);
      double y = data.getDoubleExtra("y", 0.0);
      Log.d(TAG, "onActivityResult: " + city + "|" + sid + "|" + x + "|" + y);
      // 显示
      mTvCity.setText(city);
      LatitudeAndLongitude[1] = x;
      LatitudeAndLongitude[0] = y;
      List<City> list = Constant.getInstance().getCityList();
      for (int i = 0; i < list.size(); i++) {
        if (city.contains(list.get(i).getSname())) {
          mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
          break;
        }
      }
      // 存储
      SPUtils.setString(getContext(), KEY_LATITUDE, LatitudeAndLongitude[1] + "");
      SPUtils.setString(getContext(), KEY_LONGITUDE, LatitudeAndLongitude[0] + "");
      SPUtils.setString(getContext(), KEY_CITY, mTvCity.getText().toString());
      SPUtils.setString(getContext(), KEY_CITY_PINYIN, mTvCityPinyin.getText().toString());
      // 网络加载
      String url = // 很多东西, 慢慢分析, 拆解
          "http://lvyou.baidu.com/destination/app/local?apiv=v2&around=0"
              + "&sid=" // 城市的id 不知道是怎么拿到的，现在这个写死是西安的
              + sid
              + "&y=" + LatitudeAndLongitude[1]
              // + "40.001743" // 维度
              + "&x=" + LatitudeAndLongitude[0]
              // + "116.488043" // 经度
              + "&format=app&m=8e66d8f81fdea5a65e83102dd354f290&"
              + Constant.getInstance().getBaidulvyoukey();
      // 获取当地主要内容
      getOtherData(url);
      // 获取当地，此刻的内容
      getLocationLive(sid);
    }
  }

  /* 重新刷新一下 */
  private void onRefresh() {
    // TODO: 2017/5/5 dengqi: 还没想好，要不要这么做，刷新功能如何实现？ 
    onLoadDataWithSP();
  }
}