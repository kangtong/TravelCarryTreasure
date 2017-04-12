package com.dq.android.travelcarrytreasure.ui.local;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.api.PhoneNumberService;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.common.PhoneNumberResponse;
import com.dq.android.travelcarrytreasure.retrofit.Api;
import com.dq.android.travelcarrytreasure.retrofit.RxHelper;
import com.dq.android.travelcarrytreasure.retrofit.RxRetrofitCache;
import com.dq.android.travelcarrytreasure.retrofit.RxSubscribe;
import com.dq.android.travelcarrytreasure.util.EasyPermissionsEx;
import com.dq.android.travelcarrytreasure.util.LocationHelper;
import com.dq.android.travelcarrytreasure.util.LocationUtils;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import com.orhanobut.logger.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class LocalFragment extends BaseFragment {

  private String[] mNeedPermissionsList =
      new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

  private CustomSearchView mSearchView;
  private Button mBtnLocation;

  public static LocalFragment newInstance() {

    Bundle args = new Bundle();

    LocalFragment fragment = new LocalFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_local;
  }

  @Override public void initViews() {
    mSearchView = findView(R.id.search_view);
    mBtnLocation = findView(R.id.btn_location);
  }

  @Override public void initListener() {
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
  }

  @Override public void initData() {

  }

  @Override public void processClick(View v) {
    if (v.getId() == R.id.btn_location) {
      // 使用了 EasyPermissionsEx 类来管理动态权限配置
      if (EasyPermissionsEx.hasPermissions(getContext(), mNeedPermissionsList)) {
        initLocation();
      } else {
        EasyPermissionsEx.requestPermissions(getContext(), "需要定位权限来获取当地天气信息", 1, mNeedPermissionsList);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
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
        Log.d("dengqi", "UpdateLocation.getLatitude():" + location.getLatitude());
        Log.d("dengqi", "UpdateLocation.getLongitude():" + location.getLongitude());
        // ToastUtils.toast(location.getLatitude() + " : " + location.getLongitude());
        //Toast.makeText(getContext(), location.getLatitude() + " : " + location.getLongitude(), Toast.LENGTH_SHORT)
        //    .show();
        // 请求 api
        //request(location.getLatitude() + "," + location.getLongitude());
        //start();

        ToastUtils.toast(local.updateWithNewLocation(location));
        Log.d("dengqi", "UpdateLocation: " + local.updateWithNewLocation(location));
      }

      @Override
      public void UpdateStatus(String provider, int status, Bundle extras) {
      }

      @Override
      public void UpdateGPSStatus(GpsStatus pGpsStatus) {

      }

      @Override
      public void UpdateLastLocation(Location location) {
        Log.d("dengqi", "UpdateLastLocation.getLatitude():" + location.getLatitude());
        Log.d("dengqi", "UpdateLastLocation.getLongitude():" + location.getLongitude());
        // ToastUtils.toast(location.getLatitude() + " : " + location.getLongitude());
        Toast.makeText(getContext(), location.getLatitude() + " : " + location.getLongitude(), Toast.LENGTH_SHORT)
            .show();
      }
    });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    // 在页面销毁时取消定位监听
    LocationUtils.getInstance(getContext()).removeLocationUpdatesListener();
    super.onDestroy();
  }

  private void request(String latlng) {
    //创建retrofit对象
    Retrofit retrofit = new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://apis.juhe.cn")
        .build();
    // 实例化我们的mApi对象
    PhoneNumberService mApi = retrofit.create(PhoneNumberService.class);

    // 调用我们的响应的方法
    Call<PhoneNumberResponse> news = mApi.getPhoneNumberBelong("13263272974", "3135e16b7d85b72565426b2c1e5fed60");
    news.enqueue(new Callback<PhoneNumberResponse>() {

      @Override public void onResponse(Call<PhoneNumberResponse> call, Response<PhoneNumberResponse> response) {
        PhoneNumberResponse pnRes = response.body();
        Log.d("dengqi", "onResponse: " + pnRes.getResult().getCompany());
        ToastUtils.toast(pnRes.getResult().toString());
      }

      @Override public void onFailure(Call<PhoneNumberResponse> call, Throwable t) {
        Logger.i("onResponse:   =" + t.getMessage());
      }
    });
  }

  private void start() {
    Observable<PhoneNumberResponse> fromNetwork = Api.getDefault()
        .getPhoneNumberBelong("18829011995", "3135e16b7d85b72565426b2c1e5fed60")
        .compose(RxHelper.<PhoneNumberResponse>handleResult());

    RxRetrofitCache.load(getContext(), "1234", 100000L, fromNetwork, false)
        .subscribe(new RxSubscribe<PhoneNumberResponse>(getContext(), "登录中") {
          @Override protected void _onNext(PhoneNumberResponse response) {
            Log.d("dengqi", response.getResultcode());
            Log.d("dengqi", response.getReason());
            Log.d("dengqi", response.getResult().getCity());
          }

          @Override
          protected void _onError(String message) {
            ToastUtils.toast(message);
          }
        });
  }
}