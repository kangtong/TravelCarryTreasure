package com.dq.android.travelcarrytreasure.ui.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.ui.map.BaiDuMapActivity;

/**
 * Created by DQDana on 2017/4/5
 */

public class MineFragment extends BaseFragment {

  Unbinder unbinder;

  public static MineFragment newInstance() {
    MineFragment fragment = new MineFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    return R.layout.fragment_mine;
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // TODO: inflate a fragment view
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    Button button=(Button) rootView.findViewById(R.id.btn_map_test);
    button.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        getActivity().startActivity(new Intent(getActivity(), BaiDuMapActivity.class));
      }
    });
    return rootView;
  }

}
