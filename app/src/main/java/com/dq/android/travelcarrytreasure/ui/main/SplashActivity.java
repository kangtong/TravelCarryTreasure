package com.dq.android.travelcarrytreasure.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.util.SPUtils;
import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by DQDana on 2017/4/15
 * 启动页: 每次开启app, 都需要展示
 */
public class SplashActivity extends BaseActivity {

  private static final String TAG = SplashActivity.class.getSimpleName();
  private static final String KEY_FIRST_IN = "first_in";

  private GifImageView mGifSplash;
  private int mLoopTimes = 0;

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
    mGifSplash = (GifImageView) findViewById(R.id.gif_guide);
    ((GifDrawable) mGifSplash.getDrawable()).addAnimationListener(new AnimationListener() {
      @Override public void onAnimationCompleted(int loopNumber) {
        Log.d(TAG, "onAnimationCompleted: " + loopNumber);
        if (++mLoopTimes == 3) {
          initJumpLogic();
        }
      }
    });
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
