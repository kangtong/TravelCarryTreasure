package com.dq.android.travelcarrytreasure.ui.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.ui.main.MainActivity;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/5
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

  private boolean isLoggedIn; // 默认尚未登录

  /* 已登录 */
  private RelativeLayout mLayoutAvatar;
  private RelativeLayout mLayoutUserInfo;
  private LinearLayout mLayoutTravelMap;
  private LinearLayout mLayoutAlbum;
  private LinearLayout mLayoutNotes;
  private LinearLayout mLayoutCollection;
  private LinearLayout mLayoutAbout;
  private LinearLayout mLayoutFeedback;
  private LinearLayout mLayoutLogout;

  private ImageView mImgAvatar;

  /* 未登录 */
  private TextView mTvLogin;
  private TextView mTvRegister;

  public static MineFragment newInstance() {
    MineFragment fragment = new MineFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    if (!isLoggedIn) {
      return R.layout.fragment_mine;
    } else {
      return R.layout.fragment_mine_temp;
    }
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    if (!isLoggedIn) {
      initViewNormal(view);
    } else {
      initViewTemp(view);
    }
  }

  private void initViewNormal(View view) {
    // 布局
    mLayoutAvatar = (RelativeLayout) view.findViewById(R.id.rl_avatar);
    mLayoutUserInfo = (RelativeLayout) view.findViewById(R.id.rl_user_info);
    mLayoutTravelMap = (LinearLayout) view.findViewById(R.id.ll_travel_map);
    mLayoutAlbum = (LinearLayout) view.findViewById(R.id.ll_album);
    mLayoutNotes = (LinearLayout) view.findViewById(R.id.ll_notes);
    mLayoutCollection = (LinearLayout) view.findViewById(R.id.ll_collection);
    mLayoutAbout = (LinearLayout) view.findViewById(R.id.ll_about);
    mLayoutFeedback = (LinearLayout) view.findViewById(R.id.ll_feedback);
    mLayoutLogout = (LinearLayout) view.findViewById(R.id.ll_logout);
    // 控件
    mImgAvatar = (ImageView) view.findViewById(R.id.img_avatar);

    // 单击事件
    mLayoutAvatar.setOnClickListener(this);
    mLayoutUserInfo.setOnClickListener(this);
    mLayoutTravelMap.setOnClickListener(this);
    mLayoutAlbum.setOnClickListener(this);
    mLayoutNotes.setOnClickListener(this);
    mLayoutCollection.setOnClickListener(this);
    mLayoutAbout.setOnClickListener(this);
    mLayoutFeedback.setOnClickListener(this);
    mLayoutLogout.setOnClickListener(this);

    //Intent intent = new Intent(getContext(), ImageGridActivity.class);
    // intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
    //startActivityForResult(intent, 1006);

    // 获取相关数据，头像昵称等
    onLoadData();
  }

  private void initViewTemp(View view) {
    mTvLogin = (TextView) view.findViewById(R.id.tv_login);
    mTvRegister = (TextView) view.findViewById(R.id.tv_register);
    mTvLogin.setOnClickListener(this);
    mTvRegister.setOnClickListener(this);
  }

  /* 更换头像的回掉 */
  private void updateAvatar() {
  }

  /* 加载用户数据：头像，昵称，居住地，等级 */
  private void onLoadData() {
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.rl_avatar:
        ToastUtils.toast("切换头像");
        break;
      case R.id.rl_user_info:
        ToastUtils.toast("修改居住地");
        break;
      case R.id.ll_travel_map:
        ToastUtils.toast("此功能正在开发，敬请期待");
        break;
      case R.id.ll_album:
        ToastUtils.toast("此功能正在开发，敬请期待");
        break;
      case R.id.ll_notes:
        ToastUtils.toast("此功能正在开发，敬请期待");
        break;
      case R.id.ll_collection:
        ToastUtils.toast("此功能正在开发，敬请期待");
        break;
      case R.id.ll_about:
        AboutActivity.start(getContext());
        break;
      case R.id.ll_feedback:
        FeedBackActivity.start(getContext());
        break;
      case R.id.ll_logout:
        // 清空登录数据
        // TODO: 2017/5/8 dengqi：清空登录数据
        MainActivity.start(getContext());
        break;
      case R.id.tv_login:
        ToastUtils.toast("登录页面");
        break;
      case R.id.tv_register:
        ToastUtils.toast("注册页面");
        break;
      default:
        break;
    }
  }
}
