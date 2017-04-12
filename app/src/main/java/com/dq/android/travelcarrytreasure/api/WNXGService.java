package com.dq.android.travelcarrytreasure.api;

import com.dq.android.travelcarrytreasure.model.CityResponse;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DQDana on 2017/4/6
 */

public interface WNXGService {

  // http://v.juhe.cn/WNXG/city?key=7E:D0:A2:FD:42:CA:22:C8:A2:F4:A0:B5:50:5A:2E:A9:F4:01:6C:4C

  @FormUrlEncoded
  @GET("/WNXG/city") Call<CityResponse> getCityName(
      @Query("key") String key
  );
}
