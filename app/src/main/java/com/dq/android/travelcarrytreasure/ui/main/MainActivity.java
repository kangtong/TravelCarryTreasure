package com.dq.android.travelcarrytreasure.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.ui.discover.DiscoverFragment;
import com.dq.android.travelcarrytreasure.ui.local.LocalFragment;
import com.dq.android.travelcarrytreasure.ui.mine.MineFragment;

public class MainActivity extends BaseActivity {

  private static final String TAG_DISCOVER_FRAGMENT = DiscoverFragment.class.getSimpleName();
  private static final String TAG_LOCAL_FRAGMENT = LocalFragment.class.getSimpleName();
  private static final String TAG_MINE_FRAGMENT = MineFragment.class.getSimpleName();

  // 底部导航栏
  private BottomNavigationView mNavigation;
  // 当前显示的fragment页面
  private String mCurrentFragment = "";

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
      = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_discover:
          switchCurrentFragment(TAG_DISCOVER_FRAGMENT);
          return true;
        case R.id.navigation_local:
          switchCurrentFragment(TAG_LOCAL_FRAGMENT);
          return true;
        case R.id.navigation_mine:
          switchCurrentFragment(TAG_MINE_FRAGMENT);
          return true;
      }
      return false;
    }
  };

  public static void start(BaseActivity activity) {
    Intent intent = new Intent(activity, MainActivity.class);
    activity.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    initViews();
    initListener();
    initData();
  }

  private void initViews() {
    mNavigation = (BottomNavigationView) findViewById(R.id.navigation);
  }

  private void initListener() {
    mNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
  }

  private void initData() {
    changeFragment(TAG_DISCOVER_FRAGMENT);
  }

  private void switchCurrentFragment(String tags) {
    if (!mCurrentFragment.equals(tags)) {
      changeFragment(tags);
    }
  }

  private void setCurrentFragment(String pCurrentFragment) {
    this.mCurrentFragment = pCurrentFragment;
  }

  private void changeFragment(String tag) {
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    Fragment fragment = fragmentManager.findFragmentByTag(tag); // 先获取对应的 fragment
    // 1, 隐藏
    if (!mCurrentFragment.isEmpty()) { // 非首次添加
      transaction.hide(fragmentManager.findFragmentByTag(mCurrentFragment));
    }
    // 2, 展示
    if (fragment == null) { // 有没有旧的可以直接拿来用的
      if (tag.equals(TAG_DISCOVER_FRAGMENT)) {
        fragment = DiscoverFragment.newInstance();
      } else if (tag.equals(TAG_LOCAL_FRAGMENT)) {
        fragment = LocalFragment.newInstance();
      } else if (tag.equals(TAG_MINE_FRAGMENT)) {
        fragment = MineFragment.newInstance();
      }
      transaction.add(R.id.container, fragment, tag);
    } else {
      transaction.show(fragment);
    }

    if (fragment != null) {
      setCurrentFragment(fragment.getClass().getSimpleName());
      fragment.setUserVisibleHint(true); // 我是感觉懒加载 没什么用, 当不使用viewpager的时候
    }

    transaction.commitAllowingStateLoss();
  }

  /**
   * 此代码不能删除，防止主页面在后台停留很久，fragment回收时重新生成新的fragment
   */
  @Override protected void onSaveInstanceState(Bundle outState) {

  }
}
