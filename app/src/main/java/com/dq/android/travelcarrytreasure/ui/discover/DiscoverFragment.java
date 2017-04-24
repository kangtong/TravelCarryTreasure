package com.dq.android.travelcarrytreasure.ui.discover;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverCallBack;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import com.dq.android.travelcarrytreasure.model.baidulvyou.TravellerNoteCallBack;
import com.dq.android.travelcarrytreasure.model.baidulvyou.TravellerNoteResponse;
import com.dq.android.travelcarrytreasure.ui.web.WebActivity;
import com.dq.android.travelcarrytreasure.util.NetworkUtil;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import com.dq.android.travelcarrytreasure.util.TimeUtils;
import com.dq.android.travelcarrytreasure.widget.CustomSearchView;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;
import java.util.Random;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;
import support.ui.utilities.ToastUtils;
import support.ui.widget.SwipeRefreshLayout;

/**
 * Created by DQDana on 2017/4/5
 */
public class DiscoverFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

  private static final String TAG = DiscoverFragment.class.getSimpleName();
  private static final String KEY_DISCOVER_RESPONSE = "key_discover_response";
  private static final String KEY_TRAVELLER_NOTE = "key_traveller_note";

  private SwipeRefreshLayout mRefreshLayout;
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

  @TargetApi(Build.VERSION_CODES.M)
  @Override protected void initView(View view, Bundle savedInstanceState) {
    // 初始化
    mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
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

    // mRefreshLayout 相关设置
    mRefreshLayout.setOnRefreshListener(this);
    mRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
        android.R.color.holo_red_light,
        android.R.color.holo_orange_light, android.R.color.holo_green_light);

    // 初始化 adapter
    final LinearLayoutManager mLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    mLayoutManager.setSmoothScrollbarEnabled(true);
    mLayoutManager.setAutoMeasureEnabled(true);
    mRecyclerTravels.setLayoutManager(mLayoutManager);
    mRecyclerTravels.setHasFixedSize(true);
    mRecyclerTravels.setNestedScrollingEnabled(false);
    mRecyclerTravels.setItemAnimator(new DefaultItemAnimator());
    mAdapter =
        new EasyRecyclerAdapter(getContext(), TravellerNoteResponse.DataBean.ListBean.class,
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
      onLoadData(); // 请求楼层
      onLoadTravellerNote(false); // 请求精品游记
    } else {
      ToastUtils.toast("本地网络检查错误!!!");
    }
  }

  /* 加载上次的缓存, 已存储在本地的sp */
  private void onLoadDataWithSP() {
    String json = SPUtils.getString(getContext(), KEY_DISCOVER_RESPONSE);
    if (!json.isEmpty()) {
      DiscoverResponse response = JSON.parseObject(json, DiscoverResponse.class);
      onShowData(response.getData().getMod_list());
    }

    String json_note = SPUtils.getString(getContext(), KEY_TRAVELLER_NOTE);
    if (!json_note.isEmpty()) {
      TravellerNoteResponse response = JSON.parseObject(json_note, TravellerNoteResponse.class);
      onShowTravellerNote(response.getData().getList(), true);
    }
  }

  /* 加载网络数据 */
  private void onLoadData() {
    String url_1 = // 3个楼层的东西
        "http://lvyou.baidu.com/main/app/index?apiv=v4&d=android&v=7.3.0&LVCODE=c1ea31a01427b72c1a9c812df378e3a7&T=1492943831";
    OkHttpUtils
        .get()
        .url(url_1)
        .build()
        .execute(new DiscoverCallBack() {
          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "发现界面请求,楼层数据,发生网络错误");
          }

          @Override public void onResponse(DiscoverResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "发现界面请求,楼层数据,api返回数据成功~");
              // 展示数据
              onShowData(response.getData().getMod_list());
              // 保存至SP
              String discover = JSON.toJSONString(response);
              SPUtils.setString(getContext(), KEY_DISCOVER_RESPONSE, discover);
            } else {
              Log.d(TAG, "onResponse: " + "发现界面请求,楼层数据,api返回数据失败!!!");
            }
          }
        });
  }

  /* 加载网络数据: 精品游记 */
  private void onLoadTravellerNote(final boolean isLoadMore) {
    int random = new Random().nextInt(100); // 随机咯~
    String url_2 = // 精品游记的东西
        "http://lvyou.baidu.com/main/app/praisedlist?apiv=v2&page_num="
            + random
            + "&format=app&d=android&LVCODE=fe7a5bd28a46a002f519638e049a30b0&T=1492944018";
    OkHttpUtils
        .get()
        .url(url_2)
        .build()
        .execute(new TravellerNoteCallBack() {
          @Override public void onError(okhttp3.Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "发现界面请求,精品游记数据,发生网络错误");
            mRefreshLayout.setRefreshing(false);
          }

          @Override public void onResponse(TravellerNoteResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onError: " + "发现界面请求,精品游记数据,api返回数据成功~");
              // 展示数据
              onShowTravellerNote(response.getData().getList(), isLoadMore);
              // 保存至SP
              String note = JSON.toJSONString(response);
              SPUtils.setString(getContext(), KEY_TRAVELLER_NOTE, note);
            } else {
              Log.d(TAG, "onResponse: " + "发现界面请求,精品游记数据,api返回数据失败!!!");
            }
            mRefreshLayout.setRefreshing(false);
            if (!isLoadMore) {
              // 额外多请求一次
              onLoadTravellerNote(true);
            } else {
              // TODO: 2017/4/21 dengqi: 底部显示, 更多按钮, 跳转至另一界面
            }
          }
        });
  }

  /* 对返回的数据 进行 处理 + 显示 */
  private void onShowData(List<DiscoverResponse.DataBean.ModListBean> data) {
    // 处理前三个模板的数据
    initFloor(data);
  }

  /* 对返回的数据: 精品游记 进行 处理 + 显示 */
  private void onShowTravellerNote(List<TravellerNoteResponse.DataBean.ListBean> data,
      boolean isLoadMore) {
    if (!isLoadMore) {
      initNotes(data);
    } else {
      loadMoreNotes(data);
    }
  }

  /* 楼层数据 更新 */
  private void initFloor(final List<DiscoverResponse.DataBean.ModListBean> data) {
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
    RelativeLayout layout_1 = (RelativeLayout) mFloor_3.findViewById(R.id.item_1);
    layout_1.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String url = "http://lvyou.baidu.com/main/webapp/explore/card/detail?&card_id="
            + data.get(4).getList().get(0).getCard_id()
            + "&webview=1&hybrid_os=android&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=62d1116296d06745f08841d1055973ee&T=1493034686&locEnabled=YES&locType=GPS";
        WebActivity.start(getContext(), url);
      }
    });

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
    RelativeLayout layout_2 = (RelativeLayout) mFloor_3.findViewById(R.id.item_2);
    layout_2.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String url = "http://lvyou.baidu.com/main/webapp/explore/card/detail?&card_id="
            + data.get(4).getList().get(1).getCard_id()
            + "&webview=1&hybrid_os=android&format=app&d=android&w=1080&h=1830&u=HUAWEI+NXT-AL10&v=7.3.0&i=860482033314237&s=7.0&q=1028&m=8e66d8f81fdea5a65e83102dd354f290&netTpye=wifi&LVCODE=62d1116296d06745f08841d1055973ee&T=1493034686&locEnabled=YES&locType=GPS";
        WebActivity.start(getContext(), url);
      }
    });
  }

  /* Recycle View 更新数据 */
  private void initNotes(List<TravellerNoteResponse.DataBean.ListBean> list) {
    mAdapter.addAll(list);
  }

  /* Recycle View 加载更多 */
  private void loadMoreNotes(List<TravellerNoteResponse.DataBean.ListBean> list) {
    mAdapter.appendAll(list);
  }

  @Override public void onRefresh(SwipeRefreshLayout.Direction direction) {
    onLoadData();
    onLoadTravellerNote(false);
  }
}
