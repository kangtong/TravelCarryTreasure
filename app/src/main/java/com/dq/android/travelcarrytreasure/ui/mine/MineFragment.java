package com.dq.android.travelcarrytreasure.ui.mine;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.base.BaseFragment;
import com.dq.android.travelcarrytreasure.ui.map.BaiDuMapActivity;
import com.dq.android.travelcarrytreasure.model.wyt.UserInfo;
import com.dq.android.travelcarrytreasure.ui.local.ChooseCityActivity;
import com.dq.android.travelcarrytreasure.ui.main.MainActivity;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import support.ui.utilities.ToastUtils;

import static com.dq.android.travelcarrytreasure.ui.mine.LoginActivity.KEY_USER_INFO;

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
  private TextView mTvUserLevel;
  private TextView mTvNickName;
  private TextView mTvResidence;// 居住地
  private TextView mTvModify;// 修改

  /* 未登录 */
  private TextView mTvLogin;
  private TextView mTvRegister;

  Unbinder unbinder;

  public static MineFragment newInstance() {
    MineFragment fragment = new MineFragment();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public int getLayoutId() {
    isLoggedIn = !SPUtils.getString(getContext(), KEY_USER_INFO).isEmpty();
    if (isLoggedIn) {
      return R.layout.fragment_mine;
    } else {
      return R.layout.fragment_mine_temp;
    }
  }

  @Override protected void initView(View view, Bundle savedInstanceState) {
    if (isLoggedIn) {
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
    mTvUserLevel = (TextView) view.findViewById(R.id.tv_user_level);
    mTvNickName = (TextView) view.findViewById(R.id.tv_nick_name);
    mTvResidence = (TextView) view.findViewById(R.id.tv_residence);
    mTvModify = (TextView) view.findViewById(R.id.tv_modify);

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
    mTvResidence.setOnClickListener(this);
    mTvModify.setOnClickListener(this);

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
    String json = SPUtils.getString(getContext(), KEY_USER_INFO);
    if (!json.isEmpty()) {
      UserInfo response = JSON.parseObject(json, UserInfo.class);
      onShowUserInfo(response);
    }
  }

  private void onShowUserInfo(UserInfo response) {
    mTvNickName.setText(response.getUserName());
    mTvUserLevel.setText("Level " + response.getUserLevel());
    if (!response.getUserResidence().isEmpty()) {
      mTvResidence.setText("居住地：" + response.getUserResidence());
    }
    if (!response.getUserAvatar().isEmpty()) {
      Glide.with(this)
          .load(response.getUserAvatar())
          .bitmapTransform(new CropCircleTransformation(getContext()))
          .into(mImgAvatar);
    }
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.rl_avatar:
        ToastUtils.toast("切换头像");
        break;
      case R.id.tv_residence:
      case R.id.tv_modify:
        ChooseCityActivity.start((BaseActivity) getContext(), 1025);
        break;
      case R.id.ll_travel_map:
        ToastUtils.toast("此功能正在开发，敬请期待");
        break;
      case R.id.ll_album:
        ToastUtils.toast("此功能正在开发，敬请期1待");
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
        SPUtils.removeString(getContext(), KEY_USER_INFO);
        MainActivity.start((BaseActivity) getContext(), true);
        break;
      case R.id.tv_login:
        LoginActivity.start(getContext(), LoginActivity.KEY_LOGIN);
        break;
      case R.id.tv_register:
        LoginActivity.start(getContext(), LoginActivity.KEY_REGISTER);
        break;
      default:
        break;
    }
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


  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 1025 && resultCode == Activity.RESULT_OK) {
      // 展示
      String city = data.getStringExtra("city");
      mTvResidence.setText(city);
      // 存储
      String json = SPUtils.getString(getContext(), KEY_USER_INFO);
      if (!json.isEmpty()) {
        UserInfo response = JSON.parseObject(json, UserInfo.class);
        response.setUserResidence(city);
        String discover = JSON.toJSONString(response);
        SPUtils.setString(getContext(), KEY_USER_INFO, discover);
      }
      // 修改服务器
      // 开始请求 网络

    }
  }
}
