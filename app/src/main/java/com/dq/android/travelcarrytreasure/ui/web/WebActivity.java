package com.dq.android.travelcarrytreasure.ui.web;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.base.BaseActivity;
import support.ui.utilities.ToastUtils;

/**
 * Created by DQDana on 2017/4/24
 */

public class WebActivity extends BaseActivity {

  private static final String TAG = WebActivity.class.getSimpleName();
  private static final String KEY_URL = "url";

  private WebView mWebH5;
  private int times = 0;

  public static void start(Context context, String url) {
    Intent starter = new Intent(context, WebActivity.class);
    starter.putExtra(KEY_URL, url);
    context.startActivity(starter);
  }

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);
    initView();
    onLoadData();
  }

  private void initView() {
    mWebH5 = (WebView) findViewById(R.id.web_h5);
    mWebH5.getSettings().setJavaScriptEnabled(true);
    //如果不设置WebViewClient，请求会跳转系统浏览器
    mWebH5.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if (times >= 1) {
          ToastUtils.toast("此功能尚未完成~");
          return true;
        } else {
          times++;
          return false;
        }
      }
    });
  }

  private void onLoadData() {
    String url = getIntent().getStringExtra(KEY_URL);
    Log.d(TAG, "onLoadData: " + url);
    if (url != null) {
      mWebH5.loadUrl(url);
    }
  }
}
