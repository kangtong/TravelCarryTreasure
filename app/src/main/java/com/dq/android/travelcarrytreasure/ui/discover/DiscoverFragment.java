package com.dq.android.travelcarrytreasure.ui.discover;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverCallBack;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import com.dq.android.travelcarrytreasure.util.NetworkUtil;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.dq.android.travelcarrytreasure.util.TimeUtils;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;
import support.ui.utilities.ToastUtils;
import support.ui.widget.SwipeRefreshLayout;

/**
 * Created by DQDana on 2017/4/5
 */
public class DiscoverFragment extends BaseFragment {

  private static final String TAG = DiscoverFragment.class.getSimpleName();
  private static final String KEY_DISCOVER_RESPONSE = "key_discover_response";

  private SwipeRefreshLayout mSwipeLayout;
  private CustomSearchView mSearchView;
  private RecyclerView mRecyclerTravels; // 精华游记
  private EasyRecyclerAdapter mAdapter;
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

  @Override protected void initView(View view, Bundle savedInstanceState) {
    // 初始化
    mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
    mSearchView = (CustomSearchView) view.findViewById(R.id.search_view);
    mRecyclerTravels = (RecyclerView) view.findViewById(R.id.recycle_travels);
    mFloor_1 = view.findViewById(R.id.floor_1);
    mFloor_2 = view.findViewById(R.id.floor_2);
    mFloor_3 = view.findViewById(R.id.floor_3);

    // 监听事件
    mSearchView.setSearchListener(new CustomSearchView.SearchListener() {
      @Override public void search(String keyWords) {
      }

      @Override public void cancel() {
      }
    });

    // 独立的下拉刷新
    mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh(SwipeRefreshLayout.Direction direction) {
        onLoadData();
        ToastUtils.toast("下拉刷新");
      }
    });

    // 初始化 adapter
    mRecyclerTravels.setLayoutManager(
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    mAdapter =
        new EasyRecyclerAdapter(getContext(), DiscoverResponse.DataBean.ModListBean.ListBean.class,
            DiscoverViewHolder.class);
    mAdapter.setOnClickListener(new EasyViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int i, View view) {
        ToastUtils.toast("点击了第" + i + "个item~");
      }
    });
    mRecyclerTravels.setAdapter(mAdapter);

    // 数据请求
    onLoadDataWithSP();
    if (NetworkUtil.isNetworkAvailable()) {
      onLoadData();
    } else {
      ToastUtils.toast("本地网络检查错误!!!");
    }
  }

  private void initFloor(List<DiscoverResponse.DataBean.ModListBean> data) {
    // 2,本季热门 3,主题游 4,每日发现 5, 精华游记

    // 本季热门 2
    ((TextView) mFloor_1.findViewById(R.id.tv_content_1)).setText(
        data.get(2).getList().get(0).getSname());
    ((TextView) mFloor_1.findViewById(R.id.tv_content_sub_1)).setText(
        data.get(2).getList().get(0).getAbs_desc());
    ImageView img1_1 = (ImageView) mFloor_1.findViewById(R.id.img_content_1);
    Glide.with(DiscoverFragment.this)
        .load(data.get(2).getList().get(0).getPic_url())
        .thumbnail(0.1f)
        .into(img1_1);

    ((TextView) mFloor_1.findViewById(R.id.tv_content_2)).setText(
        data.get(2).getList().get(1).getSname());
    ((TextView) mFloor_1.findViewById(R.id.tv_content_sub_2)).setText(
        data.get(2).getList().get(1).getAbs_desc());
    ImageView img1_2 = (ImageView) mFloor_1.findViewById(R.id.img_content_2);
    Glide.with(DiscoverFragment.this)
        .load(data.get(2).getList().get(1).getPic_url())
        .thumbnail(0.1f)
        .into(img1_2);

    ((TextView) mFloor_1.findViewById(R.id.tv_content_3)).setText(
        data.get(2).getList().get(2).getSname());
    ((TextView) mFloor_1.findViewById(R.id.tv_content_sub_3)).setText(
        data.get(2).getList().get(2).getAbs_desc());
    ImageView img1_3 = (ImageView) mFloor_1.findViewById(R.id.img_content_3);
    Glide.with(DiscoverFragment.this)
        .load(data.get(2).getList().get(2).getPic_url())
        .thumbnail(0.1f)
        .into(img1_3);

    // 主题游 3
    ((TextView) mFloor_2.findViewById(R.id.tv_content_1)).setText(
        data.get(3).getList().get(0).getTitle());
    ImageView img2_1 = (ImageView) mFloor_2.findViewById(R.id.img_content_1);
    Glide.with(DiscoverFragment.this)
        .load(data.get(3).getList().get(0).getPic_url())
        .thumbnail(0.1f)
        .into(img2_1);

    ((TextView) mFloor_2.findViewById(R.id.tv_content_2)).setText(
        data.get(3).getList().get(1).getTitle());
    ImageView img2_2 = (ImageView) mFloor_2.findViewById(R.id.img_content_2);
    Glide.with(DiscoverFragment.this)
        .load(data.get(3).getList().get(1).getPic_url())
        .thumbnail(0.1f)
        .into(img2_2);

    ((TextView) mFloor_2.findViewById(R.id.tv_content_3)).setText(
        data.get(3).getList().get(2).getTitle());
    ImageView img2_3 = (ImageView) mFloor_2.findViewById(R.id.img_content_3);
    Glide.with(DiscoverFragment.this)
        .load(data.get(3).getList().get(2).getPic_url())
        .thumbnail(0.1f)
        .into(img2_3);

    // 每日发现 4
    ((TextView) mFloor_3.findViewById(R.id.tv_label_1)).setText(
        data.get(4).getList().get(0).getChannel_name());
    ((TextView) mFloor_3.findViewById(R.id.tv_content_1)).setText(
        data.get(4).getList().get(0).getTitle());
    ((TextView) mFloor_3.findViewById(R.id.tv_content_sub_1)).setText(
        data.get(4).getList().get(0).getDesc());
    ((TextView) mFloor_3.findViewById(R.id.tv_date_1)).setText(
        TimeUtils.getDateFromTime(data.get(4).getList().get(0).getCreate_time(),
            "yyyy.MM.dd HH:mm:ss")
    );
    ImageView img3_1 = (ImageView) mFloor_3.findViewById(R.id.img_content_1);
    Glide.with(DiscoverFragment.this)
        .load(data.get(4).getList().get(0).getPic_url())
        .thumbnail(0.1f)
        .into(img3_1);

    ((TextView) mFloor_3.findViewById(R.id.tv_label_2)).setText(
        data.get(4).getList().get(1).getChannel_name());
    ((TextView) mFloor_3.findViewById(R.id.tv_content_2)).setText(
        data.get(4).getList().get(1).getTitle());
    ((TextView) mFloor_3.findViewById(R.id.tv_content_sub_2)).setText(
        data.get(4).getList().get(1).getDesc());
    ((TextView) mFloor_3.findViewById(R.id.tv_date_2)).setText(
        TimeUtils.getDateFromTime(data.get(4).getList().get(1).getCreate_time(),
            "yyyy.MM.dd HH:mm:ss")
    );
    ImageView img3_2 = (ImageView) mFloor_3.findViewById(R.id.img_content_2);
    Glide.with(DiscoverFragment.this)
        .load(data.get(4).getList().get(1).getPic_url())
        .thumbnail(0.1f)
        .into(img3_2);
  }

  /**
   * 加载上次的缓存, 已存储在本地的sp
   */
  private void onLoadDataWithSP() {
    String json = SPUtils.getString(getContext(), KEY_DISCOVER_RESPONSE);
    if (!json.isEmpty()) {
      DiscoverResponse response = JSON.parseObject(json, DiscoverResponse.class);
      initFloor(response.getData().getMod_list());
      initNotes(response.getData().getMod_list().get(5).getList());
      Log.d(TAG, "onLoadDataWithSP: " + "读取本地缓存数据成功~");
    } else {
      Log.d(TAG, "onLoadDataWithSP: " + "本地无缓存数据!!!");
    }
  }

  /* 加载网络数据 */
  private void onLoadData() {
    String url =
        "http://lvyou.baidu.com/main/app/index?apiv=v4&sid=&format=&d=android&w=&h=&u=&v=7.3.0&i=&s=&q=&m=&netTpye=&LVCODE=7d5035044bdcafb4ac91cba84a388f0b&T=1492696372&locEnabled=&locType=";
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new DiscoverCallBack() {
          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + e.toString());
            Log.d(TAG, "onError: " + "网络错误");
            mSwipeLayout.setRefreshing(false);
          }

          @Override public void onResponse(DiscoverResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "api返回数据成功~");
              // 展示数据
              onShowData(response.getData().getMod_list());
              // 保存至SP
              String discover = JSON.toJSONString(response);
              SPUtils.setString(getContext(), KEY_DISCOVER_RESPONSE, discover);
              Log.d(TAG, "onResponse: " + "成功保存response至SP~");
            } else {
              Log.d(TAG, "onResponse: " + "api返回数据失败!!!");
            }
            mSwipeLayout.setRefreshing(false);
          }
        });
  }

  /* 对返回的数据 进行 处理 + 显示 */
  private void onShowData(List<DiscoverResponse.DataBean.ModListBean> data) {
    // 处理前三个模板的数据
    initFloor(data);
    // 处理精华游记
    initNotes(data.get(5).getList());
  }

  /*
   更多的游记  http://lvyou.baidu.com/main/app/praisedlist?apiv=v2&sid=&page_num=2&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=9146e33eaae27dbd6e4c0dbbb3ee4fc2&T=1492527946&locEnabled=YES&locType=GPS


   加载更多   page_num=2  递增
   */

  private void initNotes(List<DiscoverResponse.DataBean.ModListBean.ListBean> list) {
    mAdapter.addAll(list.subList(0, list.size() - 1));
  }
}
