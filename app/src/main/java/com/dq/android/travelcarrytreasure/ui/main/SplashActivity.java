package com.dq.android.travelcarrytreasure.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by DQDana on 2017/4/15
 * 启动页: 每次开启app, 都需要展示
 */
public class SplashActivity extends BaseActivity {

  private static final String TAG = SplashActivity.class.getSimpleName();
  private static final String KEY_FIRST_IN = "first_in";
  private static final int  WRITE_COARSE_LOCATION_REQUEST_CODE=200;
  private static final String LATITUDE="location";
  private static final String LONGITUDE="longitude";
  private static final String ADDRESS="address";
  private GifImageView mGifSplash;
  private int mLoopTimes = 0;
  public AMapLocationClient mLocationClient = null;
  public AMapLocationListener mLocationListener = new AMapLocationListener() {
    @Override public void onLocationChanged(AMapLocation aMapLocation) {
      if (aMapLocation != null) {
        if (aMapLocation.getErrorCode() == 0) {
          //可在其中解析amapLocation获取相应内容。
          Log.d("AmapLocation",aMapLocation.getAddress());
          SPUtils.setString(SplashActivity.this,LATITUDE,
              String.valueOf(aMapLocation.getLatitude()));
          SPUtils.setString(SplashActivity.this,LONGITUDE,
              String.valueOf(aMapLocation.getLongitude()));
          SPUtils.setString(SplashActivity.this,ADDRESS,aMapLocation.getAddress());
        }else {
          //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
          Log.e("AmapError","location Error, ErrCode:"
              + aMapLocation.getErrorCode() + ", errInfo:"
              + aMapLocation.getErrorInfo());
        }
      }
    }
  };
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 先全屏
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // 再加载布局
    setContentView(R.layout.activity_splash);
    //初始化定位
    mLocationClient = new AMapLocationClient(getApplicationContext());
    //设置定位回调监听
    mLocationClient.setLocationListener(mLocationListener);
    //这里以ACCESS_COARSE_LOCATION为例
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        != PackageManager.PERMISSION_GRANTED) {
      //申请WRITE_EXTERNAL_STORAGE权限
      ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
              Manifest.permission.ACCESS_FINE_LOCATION,
              Manifest.permission.WRITE_EXTERNAL_STORAGE,
              Manifest.permission.READ_EXTERNAL_STORAGE,
              Manifest.permission.READ_PHONE_STATE},
          WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
    }else {
      //启动定位
      mLocationClient.startLocation();
    }


  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    initViews();
  }

  private void initViews() {
    mGifSplash = (GifImageView) findViewById(R.id.gif_guide);
    ((GifDrawable) mGifSplash.getDrawable()).addAnimationListener(new AnimationListener() {
      @Override public void onAnimationCompleted(int loopNumber) {
        Log.d(TAG, "onAnimationCompleted: " + loopNumber);
        if (++mLoopTimes == 2) {
          initJumpLogic();
        }
      }
    });
  }

  // 跳转至下一阶段
  private void initJumpLogic() {
    if (firstIn()) {
      GuidePageActivity.start(SplashActivity.this, 0);
    } else {
      MainActivity.start(SplashActivity.this);
    }
    SplashActivity.this.finish();
  }

  // 判断是否第一次打开 app
  private boolean firstIn() {
    boolean isFirst = SPUtils.getBoolean(this, KEY_FIRST_IN); // 默认是false
    if (!isFirst) {
      Log.d(TAG, "firstIn: " + "第一次启动app");
      SPUtils.setBoolean(this, KEY_FIRST_IN, true);
      return true;
    } else {
      Log.d(TAG, "firstIn: " + "启动app");
      return false;
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode==WRITE_COARSE_LOCATION_REQUEST_CODE){
      mLocationClient.startLocation();
    }
  }

  @Override protected void onPause() {
    super.onPause();
    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
  }
}
