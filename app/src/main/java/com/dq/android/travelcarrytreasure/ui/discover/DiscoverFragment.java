package com.dq.android.travelcarrytreasure.ui.discover;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.api.PhoneNumberService;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import com.dq.android.travelcarrytreasure.model.common.PhoneNumberResponse;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */
public class DiscoverFragment extends BaseFragment {

  private CustomSearchView mSearchView;
  private RecyclerView mRecyclerTravels; // 精华游记
  private EasyRecyclerAdapter mAdapter;
  private List<DiscoverResponse> mDataLists;
  private View mFloor_1, mFloor_2, mFloor_3; // 本季热门 - 主题游 - 每日发现

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
    mRecyclerTravels = findView(R.id.recycle_travels);
    mFloor_1 = findView(R.id.floor_1);
    mFloor_2 = findView(R.id.floor_2);
    mFloor_3 = findView(R.id.floor_3);
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
    // 初始化前三个模板
    initFloor();

    // 初始化 adapter
    mAdapter = new EasyRecyclerAdapter(getContext(),DiscoverResponse.class,);
    mRecyclerTravels.setHasFixedSize(true);
    mRecyclerTravels.setAdapter(mAdapter);
  }

  private void initFloor() {
    // 本季热门
    //((TextView) mFloor_1.findViewById(R.id.tv_content_1)).setText();
    //((TextView) mFloor_1.findViewById(R.id.tv_content_sub_1)).setText();
    //ImageView img1_1 = (ImageView) mFloor_1.findViewById(R.id.img_content_1);
    //Glide.with(DiscoverFragment.this).load("").into(img1_1);
    //
    //((TextView) mFloor_1.findViewById(R.id.tv_content_2)).setText();
    //((TextView) mFloor_1.findViewById(R.id.tv_content_sub_2)).setText();
    //ImageView img1_2 = (ImageView) mFloor_1.findViewById(R.id.img_content_2);
    //Glide.with(DiscoverFragment.this).load("").into(img1_2);
    //
    //((TextView) mFloor_1.findViewById(R.id.tv_content_3)).setText();
    //((TextView) mFloor_1.findViewById(R.id.tv_content_sub_3)).setText();
    //ImageView img1_3 = (ImageView) mFloor_1.findViewById(R.id.img_content_3);
    //Glide.with(DiscoverFragment.this).load("").into(img1_3);

    // 主题游
    //((TextView) mFloor_2.findViewById(R.id.tv_content_1)).setText();
    //ImageView img2_1 = (ImageView) mFloor_2.findViewById(R.id.img_content_1);
    //Glide.with(DiscoverFragment.this).load("").into(img2_1);
    //
    //((TextView) mFloor_2.findViewById(R.id.tv_content_2)).setText();
    //ImageView img2_2 = (ImageView) mFloor_2.findViewById(R.id.img_content_2);
    //Glide.with(DiscoverFragment.this).load("").into(img2_2);
    //
    //((TextView) mFloor_2.findViewById(R.id.tv_content_3)).setText();
    //ImageView img2_3 = (ImageView) mFloor_2.findViewById(R.id.img_content_3);
    //Glide.with(DiscoverFragment.this).load("").into(img2_3);

    // 每日发现
    //((TextView) mFloor_3.findViewById(R.id.tv_content_1)).setText();
    //((TextView) mFloor_3.findViewById(R.id.tv_content_sub_1)).setText();
    //ImageView img3_1 = (ImageView) mFloor_3.findViewById(R.id.img_content_1);
    //Glide.with(DiscoverFragment.this).load("").into(img3_1);
    //
    //((TextView) mFloor_3.findViewById(R.id.tv_content_2)).setText();
    //((TextView) mFloor_3.findViewById(R.id.tv_content_sub_2)).setText();
    //ImageView img3_2 = (ImageView) mFloor_3.findViewById(R.id.img_content_2);
    //Glide.with(DiscoverFragment.this).load("").into(img3_2);
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
