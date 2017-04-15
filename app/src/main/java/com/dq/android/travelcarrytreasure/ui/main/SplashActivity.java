package com.dq.android.travelcarrytreasure.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.util.SPUtils;

/**
 * Created by DQDana on 2017/4/15
 * 启动页: 每次开启app, 都需要展示
 */
public class SplashActivity extends BaseActivity {

  private static final String TAG = SplashActivity.class.getSimpleName();
  private static final String KEY_FIRST_IN = "first_in";

  private ImageView mImgSplash;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 先全屏
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // 再加载布局
    setContentView(R.layout.activity_splash);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    initViews();
  }

  private void initViews() {
    mImgSplash = (ImageView) findViewById(R.id.img_splash);
    Glide.with(this).load(R.drawable.gif_splash).into(mImgSplash);
    initData();
  }

  private void initData() {
    Log.d(TAG, "initData: " + "延迟三秒");
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        initJumpLogic();
      }
    }, 3000);
  }

  // 跳转至下一阶段
  private void initJumpLogic() {
    if (firstIn()) {
      GuidePageActivity.start(SplashActivity.this, 0);
    } else {
      MainActivity.start(SplashActivity.this);
    }
    SplashActivity.this.finish();
  }

  // 判断是否第一次打开 app
  private boolean firstIn() {
    boolean isFirst = SPUtils.getBoolean(this, KEY_FIRST_IN); // 默认是false
    if (!isFirst) {
      Log.d(TAG, "firstIn: " + "第一次启动app");
      SPUtils.setBoolean(this, KEY_FIRST_IN, true);
      return true;
    } else {
      Log.d(TAG, "firstIn: " + "启动app");
      return false;
    }
  }
}
