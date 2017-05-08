package com.dq.android.travelcarrytreasure.ui.map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.dq.android.travelcarrytreasure.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocationActivity extends Activity {
  //声明AMapLocationClient类对象

  public AMapLocationClient mLocationClient = null;
  //声明定位回调监听器
  public AMapLocationListener mLocationListener = new AMapLocationListener() {
    @Override public void onLocationChanged(AMapLocation amapLocation) {
      //启动定位后获取的 aMapLocation 就是位置信息，默认包含中文的具体位置。
      if (amapLocation != null) {
        if (amapLocation.getErrorCode() == 0) {
          //可在其中解析amapLocation获取相应内容。
        }else {
          //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
          Log.e("AmapError","location Error, ErrCode:"
              + amapLocation.getErrorCode() + ", errInfo:"
              + amapLocation.getErrorInfo());
        }
      }

      //以下是获取位置的方法，我没有一一声明变量，你看你需要哪个就给哪个声明变量
      amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
      amapLocation.getLatitude();//获取纬度
      amapLocation.getLongitude();//获取经度
      amapLocation.getAccuracy();//获取精度信息
      amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
      amapLocation.getCountry();//国家信息
      amapLocation.getProvince();//省信息
      amapLocation.getCity();//城市信息
      amapLocation.getDistrict();//城区信息
      amapLocation.getStreet();//街道信息
      amapLocation.getStreetNum();//街道门牌号信息
      amapLocation.getCityCode();//城市编码
      amapLocation.getAdCode();//地区编码
      amapLocation.getAoiName();//获取当前定位点的AOI信息
      amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
      amapLocation.getFloor();//获取当前室内定位的楼层
      //获取定位时间
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = new Date(amapLocation.getTime());
      df.format(date);
    }
  };
  //声明AMapLocationClientOption对象
  public AMapLocationClientOption mLocationOption = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_location);
    initLocationOption();
    //初始化定位
    mLocationClient = new AMapLocationClient(getApplicationContext());
    //设置定位回调监听
    mLocationClient.setLocationListener(mLocationListener);
    //给定位客户端对象设置定位参数
    mLocationClient.setLocationOption(mLocationOption);
    //启动定位
    mLocationClient.startLocation();
  }

  /**
   * 这个方法是初始化对定位模块的设置选项，里面有很多个模式，你根据需要挑选一个
   */
  private void initLocationOption() {
    //初始化AMapLocationClientOption对象
    mLocationOption = new AMapLocationClientOption();

    /**
     * 下面是定位的模式，我写了注释和方法，你打开一个就好
     */
    //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息。
    mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。不会使用GPS和其他传感器，只会使用网络定位（Wi-Fi和基站定位）；
    //mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
    //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息。
    //mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

    /**
     * 设置单次定位
     如果您需要使用单次定位，需要进行如下设置：
     */
    //获取一次定位结果：
    //该方法默认为false。
    mLocationOption.setOnceLocation(true);
    //获取最近3s内精度最高的一次定位结果：
    //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
    mLocationOption.setOnceLocationLatest(true);

    /**
     * 自定义连续定位
     SDK默认采用连续定位模式，时间间隔2000ms。如果您需要自定义调用间隔：
     */
    //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
    mLocationOption.setInterval(1000);
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
