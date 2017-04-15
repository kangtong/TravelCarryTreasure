package com.dq.android.travelcarrytreasure.ui.mine;

import android.os.Bundle;
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

  @Override protected void initView(View view, Bundle savedInstanceState) {

    mMessage = (TextView) view.findViewById(R.id.message);
  }
}
