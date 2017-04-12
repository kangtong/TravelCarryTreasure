package com.dq.android.travelcarrytreasure.api;

import com.dq.android.travelcarrytreasure.model.CityResponse;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CityNameService {

  //@GET("/maps/api/geocode/json?latlng=116.40739499999995,39.904211&language=zh-CN&sensor=true")

  @FormUrlEncoded
  @GET("/maps/api/geocode/json?language=zh-CN&sensor=true")
  Call<CityResponse> getCityName(
      @Query("latlng") String latlng); // 116.40739499999995,39.904211
}