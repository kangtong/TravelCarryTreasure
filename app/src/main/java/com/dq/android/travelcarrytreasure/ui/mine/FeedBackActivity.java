package com.dq.android.travelcarrytreasure.ui.mine;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import com.dq.android.travelcarrytreasure.util.NetworkUtil;
import com.dq.android.travelcarrytreasure.widget.CustomProgressDialog;
import com.dq.android.travelcarrytreasure.widget.CustomToolBar;
import com.rengwuxian.materialedittext.MaterialEditText;
import support.ui.utilities.ToastUtils;

/**
 * 作者：Create on 2017/5/8 14:12  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class FeedBackActivity extends BaseActivity {

  private CustomToolBar mToolBar;
  private MaterialEditText mEdtFeedback;

  public static void start(Context context) {
    Intent starter = new Intent(context, FeedBackActivity.class);
    // starter.putExtra();
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feed_back);
    initView();
  }

  private void initView() {
    mToolBar = (CustomToolBar) findViewById(R.id.tool_bar);
    mToolBar.setOnToolBarListener(new CustomToolBar.onToolBarListener() {
      @Override public void onBackListener() {
        finish();
      }

      @Override public void onActionListener() {
        if (mEdtFeedback.getText().toString().isEmpty()) {
          ToastUtils.toast("请填写反馈意见！");
        }
        if (NetworkUtil.isNetworkAvailable()) {
          //文字即为显示的内容
          Dialog mDialog = CustomProgressDialog.createLoadingDialog(FeedBackActivity.this, "正在提交中...");
          mDialog.setCancelable(false);//允许返回
          mDialog.show();//显示
          new Handler().postDelayed(new Runnable() {
            public void run() {
              mDialog.dismiss();//隐藏
              ToastUtils.toast("感谢您的反馈~");
              finish();
            }
          }, 1000);
        } else {
          ToastUtils.toast("本地网络错误！");
        }
      }
    });
    mEdtFeedback = (MaterialEditText) findViewById(R.id.edt_feedback);
  }
}
