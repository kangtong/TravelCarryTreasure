package com.dq.android.travelcarrytreasure.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DQDana on 2017/4/4
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

  private boolean isVisible = false;
  private boolean isInitView = false;
  private boolean isFirstLoad = true;

  private View convertView;
  private SparseArray<View> mViews;

  public abstract int getLayoutId();

  public abstract void initViews();

  public abstract void initListener();

  public abstract void initData();

  public abstract void processClick(View v);

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (isVisibleToUser) {
      isVisible = true;
      lazyLoad();
    } else {
      isVisible = false;
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mViews = new SparseArray<>();
    convertView = inflater.inflate(getLayoutId(), container, false);
    initViews();

    isInitView = true;
    lazyLoad();

    return convertView;
  }

  @Override public void onClick(View v) {
    processClick(v);
  }

  @SuppressWarnings("unchecked") public <E extends View> E findView(int viewId) {
    if (convertView != null) {
      E view = (E) mViews.get(viewId);
      if (view == null) {
        view = (E) convertView.findViewById(viewId);
        mViews.put(viewId, view);
      }
      return view;
    }
    return null;
  }

  private void lazyLoad() {
    if (!isFirstLoad || !isVisible || !isInitView) {
      Log.d("dengqi", "不加载数据");
      //不加载数据
      return;
    }
    //加载数据
    Log.d("dengqi", "懒加载");
    initListener();
    initData();

    isFirstLoad = false;
  }
}