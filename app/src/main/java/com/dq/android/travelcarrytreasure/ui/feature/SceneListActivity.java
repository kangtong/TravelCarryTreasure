package com.dq.android.travelcarrytreasure.ui.feature;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.widget.CustomToolBar;
import support.ui.adapters.EasyRecyclerAdapter;

/**
 * 作者：Create on 2017/5/5 13:06  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：全部景点的列表
 */
public class SceneListActivity extends BaseActivity {

  private static final String TAG = SceneListActivity.class.getSimpleName();
  private static final String KEY_SID = "sid";

  private CustomToolBar mToolbar;
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
  }

  private void initViews() {
    mToolbar = (CustomToolBar) findViewById(R.id.tool_bar);
  }

  private void initListener() {
    mToolbar.setOnToolBarListener(new CustomToolBar.onToolBarListener() {
      @Override public void onBackListener() {
        onBackPressed();
      }

      @Override public void onActionListener() {

      }
    });
  }

  private void initData() {
  }
}
