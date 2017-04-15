package com.dq.android.travelcarrytreasure.ui.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;

/**
 * Created by DQDana on 2017/4/15
 * 引导页: 仅限第一次启动
 */
public class GuidePageActivity extends BaseActivity {

  private static final String EXTRA_GUIDE = "EXTRA_GUIDE";

  private ImageView mImgGuide;

  private int mGuideIndex = 0;
  private float x1 = 0;
  private float x2 = 0;

  public static void start(BaseActivity activity, int index) {
    Intent intent = new Intent(activity, GuidePageActivity.class);
    intent.putExtra(EXTRA_GUIDE, index);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      activity.startActivity(intent,
          ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
    } else {
      activity.startActivity(intent);
    }
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // 全屏
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // 跳转动画
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Transition explode = TransitionInflater.from(this).inflateTransition(R.transition.explode);
      //退出时使用
      getWindow().setExitTransition(explode);
      //第一次进入时使用
      getWindow().setEnterTransition(explode);
      //再次进入时使用
      getWindow().setReenterTransition(explode);
    }
    // 加载主布局
    setContentView(R.layout.activity_guide_page);
  }

  @Override protected void onPostCreate(@Nullable Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    initViews();
    initData();
  }

  private void initViews() {
    mImgGuide = (ImageView) findViewById(R.id.img_guide);
  }

  private void initData() {
    setImage();
  }

  private void setImage() {
    mGuideIndex = this.getIntent().getIntExtra(EXTRA_GUIDE, 0);
    switch (mGuideIndex) {
      case 0:
        Glide.with(this).load(R.drawable.empty_album_tips_1).into(mImgGuide);
        break;
      case 1:
        Glide.with(this).load(R.drawable.empty_album_tips_2).into(mImgGuide);
        break;
      case 2:
        Glide.with(this).load(R.drawable.empty_album_tips_3).into(mImgGuide);
        break;
      case 3:
        Glide.with(this).load(R.drawable.empty_album_tips_4).into(mImgGuide);
        break;
    }
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      x1 = event.getX();
    }
    if (event.getAction() == MotionEvent.ACTION_UP) {
      x2 = event.getX();
      if (x1 - x2 > 50) {
        if (mGuideIndex == 3) {
          Intent intent = new Intent(this, MainActivity.class);
          startActivity(intent);
          finish();
        } else {
          start(this, mGuideIndex + 1);
          finish();
        }
      } else if (x2 - x1 > 50) {
        if (mGuideIndex > 0) {
          start(this, mGuideIndex - 1);
          finish();
        }
      }
    }
    return super.onTouchEvent(event);
  }
}
