package com.dq.android.travelcarrytreasure.ui.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;

/**
 * Created by DQDana on 2017/4/5
 */

public class MineFragment extends BaseFragment {

  private TextView mMessage;

  public static MineFragment newInstance() {

    Bundle args = new Bundle();

    MineFragment fragment = new MineFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_mine;
  }

  @Override public void initViews() {
    mMessage = findView(R.id.message);
  }

  @Override public void initListener() {

  }

  @Override public void initData() {
    Log.d("dengqi", "走到这一步了");
    mMessage.setText("dq");
  }

  @Override public void processClick(View v) {

  }
}
