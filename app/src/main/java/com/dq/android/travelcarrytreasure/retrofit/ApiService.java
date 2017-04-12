package com.dq.android.travelcarrytreasure.retrofit;

import com.dq.android.travelcarrytreasure.model.common.PhoneNumberResponse;
import java.util.ArrayList;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jam on 16-5-17
 * Description:
 */
public interface ApiService {

  @GET("api/data/福利/{pageCount}/{pageIndex}")
  Observable<BaseModel<ArrayList<Benefit>>> rxBenefits(
      @Path("pageCount") int pageCount,
      @Path("pageIndex") int pageIndex

  );

  @GET("/mobile/get")
  Observable<BaseModel<PhoneNumberResponse>> getPhoneNumberBelong(
      @Query("phone") String phone,
      @Query("key") String key
  );
}





