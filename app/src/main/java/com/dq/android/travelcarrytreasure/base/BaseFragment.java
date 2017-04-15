package com.dq.android.travelcarrytreasure.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DQDana on 2017/4/15
 */

public abstract class BaseFragment extends Fragment {

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(getLayoutId(), container, false);
    initView(view, savedInstanceState);
    return view;
  }

  // 获取布局文件ID
  protected abstract int getLayoutId();

  // 初始化
  protected abstract void initView(View view, Bundle savedInstanceState);
}
