package com.dq.android.travelcarrytreasure.ui.feature;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.model.Constant;
import com.dq.android.travelcarrytreasure.model.baidulvyou.SceneResponse;
import com.dq.android.travelcarrytreasure.service.baidulvyou.SceneCallBack;
import com.dq.android.travelcarrytreasure.util.NetworkUtil;
import com.dq.android.travelcarrytreasure.widget.CustomToolBar;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.List;
import okhttp3.Call;
import support.ui.adapters.EasyRecyclerAdapter;
import support.ui.adapters.EasyViewHolder;
import support.ui.utilities.ToastUtils;

/**
 * 作者：Create on 2017/5/5 13:06  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：全部景点的列表
 */
public class SceneListActivity extends BaseActivity implements View.OnClickListener {

  private static final String TAG = SceneListActivity.class.getSimpleName();
  private static final String KEY_SID = "sid";

  private String sid = null;
  private int start_number = 0;

  private CustomToolBar mToolbar;
  private FrameLayout mLayoutType, mLayoutSort;
  private TwinklingRefreshLayout mLayoutRefresh;
  private ImageView mImgLoading;
  private RecyclerView mRecyclerView;
  private EasyRecyclerAdapter mAdapter;

  public static void start(Context context, String sid) {
    Intent starter = new Intent(context, SceneListActivity.class);
    starter.putExtra(KEY_SID, sid);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scene_list);
    initViews();
    initListener();
    initData();
    onLoadData();
  }

  private void initViews() {
    mToolbar = (CustomToolBar) findViewById(R.id.tool_bar);
    mLayoutType = (FrameLayout) findViewById(R.id.fl_type);
    mLayoutSort = (FrameLayout) findViewById(R.id.fl_sort);
    mLayoutRefresh = (TwinklingRefreshLayout) findViewById(R.id.twinkling_refresh_layout);
    mImgLoading = (ImageView) findViewById(R.id.iv_loading);
    mRecyclerView = (RecyclerView) findViewById(R.id.recycle_all_scene);
  }

  private void initListener() {
    mToolbar.setOnToolBarListener(new CustomToolBar.onToolBarListener() {
      @Override public void onBackListener() {
        onBackPressed();
      }

      @Override public void onActionListener() {
        ToastUtils.toast("进入西安市本地景点搜索页");
      }
    });
    mLayoutType.setOnClickListener(this);
    mLayoutSort.setOnClickListener(this);
    mLayoutRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
      @Override public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
        super.onLoadMore(refreshLayout);
        onLoadMoreData();
      }
    });
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.fl_type:
      case R.id.fl_sort:
        ToastUtils.toast("此功能正在开发，敬请期待~");
        break;
    }
  }

  private void initData() {
    // 获取 数据
    sid = getIntent().getStringExtra(KEY_SID);
    // 刷新控件的相关设置
    mLayoutRefresh.setEnableRefresh(false); // 禁止刷新
    mLayoutRefresh.setEnableOverScroll(false); // 禁止越界回弹
    // RecycleView 的相关处理
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setHasFixedSize(true);
    mAdapter = new EasyRecyclerAdapter(this, SceneResponse.DataBean.SceneListBean.class, SceneViewHolder.class);
    mAdapter.setOnClickListener(new EasyViewHolder.OnItemClickListener() {
      @Override public void onItemClick(int i, View view) {
        ToastUtils.toast("点击了第" + i + "个item~");
      }
    });
    mRecyclerView.setAdapter(mAdapter);
  }

  private void onLoadData() {
    if (!NetworkUtil.isNetworkAvailable()) {
      // 默认直接转loading
      Glide.with(this)
          .load(R.drawable.ic_starter_network_error)
          .override(getResources().getDimensionPixelOffset(R.dimen.dp100), getResources().getDimensionPixelSize(R.dimen.dp100))
          .into(mImgLoading);
      ToastUtils.toast("本地网络检查错误!!!");
      return;
    } else {
      // 默认直接转loading
      Glide.with(this).load(R.drawable.git_loading_2).into(mImgLoading);
    }
    // 开始请求数据
    String url;
    if (sid == null) {
      url = // 这个返回的东西，相对清晰简单
          "http://lvyou.baidu.com/destination/app/topscenes?apiv=v2&rn=10" // 每页10个
              + "&pn=" // 分页的起始
              + start_number
              + "&sid="
              + "9bb8ee381df41344144463f5" // 西安的固定值
              + "&" + Constant.getInstance().getBaidulvyoukey()
              + "&" + Constant.getInstance().getBaidulvyouXY();
    } else {
      url =
          "http://lvyou.baidu.com/destination/app/topscenes?apiv=v2&rn=10" // 每页10个
              + "&pn=" // 分页的起始
              + start_number
              + "&sid="
              + sid
              + "&" + Constant.getInstance().getBaidulvyoukey()
              + "&" + Constant.getInstance().getBaidulvyouXY();
    }
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new SceneCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "全部景点列表页 请求,发生网络错误");
            Log.d(TAG, "onError: " + e.toString());
            Glide.with(SceneListActivity.this)
                .load(R.drawable.ic_starter_network_error)
                .override(getResources().getDimensionPixelOffset(R.dimen.dp100), getResources().getDimensionPixelSize(R.dimen.dp100))
                .into(mImgLoading);
          }

          @Override public void onResponse(SceneResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "全部景点列表页 请求,api返回数据成功~");
              // 展示数据
              start_number += 10;
              onShowData(response.getData().getScene_list());
            } else {
              Log.d(TAG, "onResponse: " + "全部景点列表页 请求,api返回数据失败!!!");
            }
          }
        });
  }

  private void onShowData(List<SceneResponse.DataBean.SceneListBean> data) {
    mImgLoading.setVisibility(View.GONE);
    mLayoutRefresh.setVisibility(View.VISIBLE);
    mAdapter.addAll(data);
  }

  private void onLoadMoreData() {
    // 开始请求数据
    String url;
    if (sid == null) {
      url = // 这个返回的东西，相对清晰简单
          "http://lvyou.baidu.com/destination/app/topscenes?apiv=v2&rn=10" // 每页10个
              + "&pn=" // 分页的起始
              + start_number
              + "&sid="
              + "9bb8ee381df41344144463f5" // 西安的固定值
              + "&" + Constant.getInstance().getBaidulvyoukey()
              + "&" + Constant.getInstance().getBaidulvyouXY();
    } else {
      url =
          "http://lvyou.baidu.com/destination/app/topscenes?apiv=v2&rn=10" // 每页10个
              + "&pn=" // 分页的起始
              + start_number
              + "&sid="
              + sid
              + "&" + Constant.getInstance().getBaidulvyoukey()
              + "&" + Constant.getInstance().getBaidulvyouXY();
    }
    OkHttpUtils
        .get()
        .url(url)
        .build()
        .execute(new SceneCallBack() {
          @Override public void onError(Call call, Exception e, int id) {
            Log.d(TAG, "onError: " + "全部景点列表页 加载更多 请求,发生网络错误");
            Log.d(TAG, "onError: " + e.toString());
          }

          @Override public void onResponse(SceneResponse response, int id) {
            if (response.getErrno() == 0) { // 成功
              Log.d(TAG, "onResponse: " + "全部景点列表页 加载更多 请求,api返回数据成功~");
              // 展示数据
              start_number += 10;
              onShowLoadMore(response.getData().getScene_list());
            } else {
              Log.d(TAG, "onResponse: " + "全部景点列表页 加载更多 请求,api返回数据失败!!!");
            }
          }
        });
  }

  private void onShowLoadMore(List<SceneResponse.DataBean.SceneListBean> data) {
    mLayoutRefresh.finishLoadmore();
    mAdapter.appendAll(data);
  }
}
