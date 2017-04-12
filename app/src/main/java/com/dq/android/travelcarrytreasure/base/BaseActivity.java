package com.dq.android.travelcarrytreasure.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by DQDana on 2017/4/4
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

  private SparseArray<View> mViews;

  public abstract int getLayoutId();

  public abstract void initViews();

  public abstract void initListener();

  public abstract void initData();

  public abstract void processClick(View v);

  public void onClick(View v) {
    processClick(v);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mViews = new SparseArray<>();
    setContentView(getLayoutId());
    initViews();
    initListener();
    initData();
  }

  @SuppressWarnings("unchecked") public <E extends View> E findView(int viewId) {
    E view = (E) mViews.get(viewId);
    if (view == null) {
      view = (E) findViewById(viewId);
      mViews.put(viewId, view);
    }
    return view;
  }
}
