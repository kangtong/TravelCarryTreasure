package com.dq.android.travelcarrytreasure.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DQDana on 2017/4/15
 */

public class BaseActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setDarkStatusIcon(true); // 白色状态栏
  }

  private void setDarkStatusIcon(boolean bDark) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      View decorView = getWindow().getDecorView();
      if (decorView != null) {
        int vis = decorView.getSystemUiVisibility();
        if (bDark) {
          vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
          vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decorView.setSystemUiVisibility(vis);
      }
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
  }
}
