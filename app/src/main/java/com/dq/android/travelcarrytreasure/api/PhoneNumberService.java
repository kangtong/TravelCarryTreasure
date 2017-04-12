package com.dq.android.travelcarrytreasure.api;

import com.dq.android.travelcarrytreasure.model.common.PhoneNumberResponse;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by DQDana on 2017/4/6
 */

public interface PhoneNumberService {

  // http://apis.juhe.cn/mobile/get

  @GET("/mobile/get") Call<PhoneNumberResponse> getPhoneNumberBelong(
      @Query("phone") String phone,
      @Query("key") String key
  );

  // 一下的都是模板 例子 , 没有实际作用

  // 什么参数都没有
  @GET("/mobile/get") Call<PhoneNumberResponse> getPhoneNumberBelong();

  // 替换
  @GET("/mobile/{phone}/users") Call<PhoneNumberResponse> withParam(
      @Path("phone") String phone
  );

  // 后面追加参数 : @GET("api/retrofitTesting?param=value")
  @GET("/mobile/users") Call<PhoneNumberResponse> appendParam(
      @Query("param") String param
  );

  // 后面追加多个参数 : @GET("api/retrofitTesting?param1=value&param2=value&param3=value")
  @GET("/mobile/users") Call<PhoneNumberResponse> appendParams(
      @QueryMap Map<String, String> param
  ); // 调用时, 参数给一个 map  就行了

  // post 上传一个实体
  @POST("/mobile/add/user")
  Call<PhoneNumberResponse> uploadBody(
      @Body PhoneNumberResponse response
  );

  @FormUrlEncoded
  @POST("/mobile/add/user")
  Call<PhoneNumberResponse> uploadBody(
      @Field("username") String username,
      @Field("age") String age
  );
}
