package com.dq.android.travelcarrytreasure.ui.discover;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.api.PhoneNumberService;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.common.PhoneNumberResponse;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class DiscoverFragment extends BaseFragment {

  private CustomSearchView mSearchView;
  private RecyclerView mRecyclerViewFloor;
  private EasyRecyclerAdapter mAdapter;

  public static DiscoverFragment newInstance() {
    DiscoverFragment fragment = new DiscoverFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_discover;
  }

  @Override public void initViews() {
    mSearchView = findView(R.id.search_view);
    mRecyclerViewFloor = findView(R.id.recycle_floor);
  }

  @Override public void initListener() {
    mSearchView.setSearchListener(new CustomSearchView.SearchListener() {
      @Override public void search(String keyWords) {
        request();
      }

      @Override public void cancel() {

      }
    });
  }

  @Override public void initData() {
    // 初始化 adapter
    mRecyclerViewFloor.setAdapter(mAdapter);
  }

  @Override public void processClick(View v) {
  }

  private void request() {
    // step1
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://apis.juhe.cn")
        .addConverterFactory(GsonConverterFactory.create()) // 返回的似的 实体
        // .addConverterFactory(ScalarsConverterFactory.create()) // 返回的 string
        .build();
    // step2
    PhoneNumberService service = retrofit.create(PhoneNumberService.class);
    // step3
    Call<PhoneNumberResponse> call =
        service.getPhoneNumberBelong("13263272974", "3135e16b7d85b72565426b2c1e5fed60");
    // step4
    call.enqueue(new Callback<PhoneNumberResponse>() {
      @Override
      public void onResponse(Call<PhoneNumberResponse> call,
          Response<PhoneNumberResponse> response) {
        ToastUtils.toast(response.body().getResult().toString());
      }

      @Override public void onFailure(Call<PhoneNumberResponse> call, Throwable t) {
        ToastUtils.toast("网络错误");
      }
    });
  }
}
