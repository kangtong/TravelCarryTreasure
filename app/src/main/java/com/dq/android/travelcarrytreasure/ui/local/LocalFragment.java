package com.dq.android.travelcarrytreasure.ui.local;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.util.EasyPermissionsEx;
import com.dq.android.travelcarrytreasure.util.LocationHelper;
import com.dq.android.travelcarrytreasure.util.LocationUtils;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class LocalFragment extends BaseFragment implements View.OnClickListener {

  private String[] mNeedPermissionsList =
      new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
          Manifest.permission.ACCESS_COARSE_LOCATION};

  private CustomSearchView mSearchView;
  private Button mBtnLocation;
  private TextView mTvCity;

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

    mSearchView = (CustomSearchView) view.findViewById(R.id.search_view);
    mBtnLocation = (Button) view.findViewById(R.id.btn_location);
    mTvCity = (TextView) view.findViewById(R.id.tv_city);

    mSearchView.setHint("搜索当地景点、美食、攻略");
    mSearchView.setPriorityJump(new View.OnClickListener() {
      @Override public void onClick(View v) {
        ToastUtils.toast("跳转至搜索界面");
      }
    });
    mSearchView.setSearchListener(new CustomSearchView.SearchListener() {
      @Override public void search(String keyWords) {
        ToastUtils.toast("搜索 -> " + keyWords);
      }

      @Override public void cancel() {

      }
    });

    mBtnLocation.setOnClickListener(this);
    mTvCity.setOnClickListener(this);
  }

  @Override public void onClick(View v) {

    switch (v.getId()) {
      case R.id.btn_location:
        // 使用了 EasyPermissionsEx 类来管理动态权限配置
        if (EasyPermissionsEx.hasPermissions(getContext(), mNeedPermissionsList)) {
          initLocation();
        } else {
          EasyPermissionsEx.requestPermissions(getContext(), "需要定位权限来获取当地天气信息", 1,
              mNeedPermissionsList);
        }
        break;
      case R.id.tv_city:
        ChooseCityActivity.start(getContext());
        break;
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    switch (requestCode) {
      case 1: {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Log.d("dengqi", "已获取权限!");
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

  private void initLocation() {
    final LocationUtils local = LocationUtils.getInstance(getContext());

    local.initLocation(new LocationHelper() {
      @Override
      public void UpdateLocation(Location location) {
        ToastUtils.toast(local.updateWithNewLocation(location));
      }

      @Override
      public void UpdateStatus(String provider, int status, Bundle extras) {
      }

      @Override
      public void UpdateGPSStatus(GpsStatus pGpsStatus) {

      }

      @Override
      public void UpdateLastLocation(Location location) {
        ToastUtils.toast(location.getLatitude() + " : " + location.getLongitude());
      }
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    // 在页面销毁时取消定位监听
    LocationUtils.getInstance(getContext()).removeLocationUpdatesListener();
    super.onDestroy();
  }
}