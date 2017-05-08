package com.dq.android.travelcarrytreasure.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.widget.CustomToolBar;
import support.ui.utilities.ToastUtils;

/**
 * 作者：Create on 2017/5/8 13:52  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class AboutActivity extends BaseActivity {

  private CustomToolBar mToolBar;
  private TextView mTvStar;
  private int times = 0;

  public static void start(Context context) {
    Intent starter = new Intent(context, AboutActivity.class);
    // starter.putExtra();
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_about);
    initView();
  }

  private void initView() {
    mToolBar = (CustomToolBar) findViewById(R.id.tool_bar);
    mTvStar = (TextView) findViewById(R.id.tv_star);

    mToolBar.setOnToolBarListener(new CustomToolBar.onToolBarListener() {
      @Override public void onBackListener() {
        finish();
      }

      @Override public void onActionListener() {

      }
    });
    mTvStar.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        times++;
        if (times % 7 == 0) {
          ToastUtils.toast("驴友，去 GitHub 给个Star吧~");
        }
      }
    });
  }
}
