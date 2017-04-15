package com.dq.android.travelcarrytreasure.api;

import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DQDana on 2017/4/15
 */

public interface DiscoverService {

  @GET("/main/app/index?apiv=v4&sid=795ac511463263cf7ae3def3&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=4d95e25f12c37daa09394aa2848e906b&T=1492223512&locEnabled=YES&locType=GPS")
  Call<DiscoverResponse> getDiscoverResponse(
      @Query("y") String weidu,
      @Query("x") String jingdu
  );

  @GET("/main/app/index?apiv=v4&sid=795ac511463263cf7ae3def3&format=app&d=android&y=34.27&x=108.93")
  Call<DiscoverResponse> getDiscoverResponse();
}
