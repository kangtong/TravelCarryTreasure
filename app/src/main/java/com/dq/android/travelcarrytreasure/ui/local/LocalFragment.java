package com.dq.android.travelcarrytreasure.ui.local;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.Constant;
import com.dq.android.travelcarrytreasure.model.common.City;
import com.dq.android.travelcarrytreasure.model.common.FuzzyAddress;
import com.dq.android.travelcarrytreasure.service.FuzzyAddressCallBack;
import com.dq.android.travelcarrytreasure.util.EasyPermissionsEx;
import com.dq.android.travelcarrytreasure.util.LocationHelper;
import com.dq.android.travelcarrytreasure.util.LocationUtils;
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

  private String[] mNeedPermissionsList =
      new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_COARSE_LOCATION};

  private ScrollableLayout mLayoutScroll;
  private RelativeLayout mLayoutCityDetails;
  private FrameLayout mLayoutSearch;
  private TextView mTvCity;
  private TextView mTvCityPinyin;

  private String ip;
  private String city;

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
    mLayoutSearch = (FrameLayout) view.findViewById(R.id.layout_search);
    mTvCity = (TextView) view.findViewById(R.id.tv_city);
    mTvCityPinyin = (TextView) view.findViewById(R.id.tv_city_pinyin);

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

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case 1: {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Log.d(TAG, "已获取权限!");
          initLocation();
        } else {
          if (EasyPermissionsEx.somePermissionPermanentlyDenied(this, mNeedPermissionsList)) {
            EasyPermissionsEx.goSettings2Permissions(this, "需要定位权限来获取当地天气信息,但是该权限被禁止,你可以到设置中更改"
                , "去设置", 1);
          }
        }
      }
      break;
    }
  }

  private void onLoadData() {
    // 1.通过高德获取城市名 + 天气
    // 使用了 EasyPermissionsEx 类来管理动态权限配置
    //if (EasyPermissionsEx.hasPermissions(getContext(), mNeedPermissionsList)) {
    //  initLocation();
    //} else {
    //  EasyPermissionsEx.requestPermissions(getContext(), "需要定位权限来获取当地天气信息", 1,
    //      mNeedPermissionsList);
    //}

    // 先获取ip地址
    getIP();

    // 2.glide加载大图
    // 3,其他数据加载
  }

  /* 定位操作 */
  private void initLocation() {
    final LocationUtils local = LocationUtils.getInstance(getContext());

    local.initLocation(new LocationHelper() {
      @Override
      public void UpdateLocation(Location location) {
        // 城市名简体汉字
        String cityName = local.updateWithNewLocation(location);
        mTvCity.setText(cityName);
        // 城市名拼音
        List<City> list = Constant.getInstance().getCityList();
        for (int i = 0; i < list.size(); i++) {
          if (cityName.contains(list.get(i).getSname())) {
            mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
            break;
          }
        }
      }

      @Override
      public void UpdateStatus(String provider, int status, Bundle extras) {
      }

      @Override
      public void UpdateGPSStatus(GpsStatus pGpsStatus) {

      }

      @Override
      public void UpdateLastLocation(Location location) {
        // 城市名简体汉字
        String cityName = local.updateWithNewLocation(location);
        mTvCity.setText(cityName);
        // 城市名拼音
        List<City> list = Constant.getInstance().getCityList();
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getSname().equals(cityName)) {
            mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
            break;
          }
        }
      }
    });
  }

  /* 改变透明度的操作 */
  private void changeSearchViewAlpha() {
    int[] location = new int[2];
    mLayoutCityDetails.getLocationOnScreen(location);

    int y = location[1] - 60; // 60 是状态栏的高度
    int absY = Math.abs(y);
    int height = mLayoutSearch.getHeight();

    if (absY <= height) {
      mLayoutSearch.getBackground().mutate().setAlpha(
          (int) ((absY + 0.0) / height * 255));
    } else {
      mLayoutSearch.getBackground().mutate().setAlpha(255);
    }
  }

  /* 销毁视图时, 断开定位 */
  @Override public void onDestroyView() {
    super.onDestroyView();
    // 在页面销毁时取消定位监听
    LocationUtils.getInstance(getContext()).removeLocationUpdatesListener();
    super.onDestroy();
  }

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
                .compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
            Matcher matcher = pattern.matcher(response);
            if (matcher.find()) {
              ip = matcher.group();
              getCityByIp(ip);
            }
          }
        });
  }

  private void getCityByIp(String ip) {
    String url = "http://restapi.amap.com/v3/ip?ip=" + ip + "&key=" + Constant.getInstance().getGaodewebkey();
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
              city = response.getCity();
              // 城市名拼音
              List<City> list = Constant.getInstance().getCityList();
              for (int i = 0; i < list.size(); i++) {
                if (response.getCity().contains(list.get(i).getSname())) {
                  mTvCityPinyin.setText(list.get(i).getSurl().toUpperCase());
                  break;
                }
              }
            } else {
              Log.d(TAG, "onResponse: " + response.getInfo());
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
          }
        });
  }
}