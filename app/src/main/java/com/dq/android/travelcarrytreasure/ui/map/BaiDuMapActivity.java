package com.dq.android.travelcarrytreasure.ui.map;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.baidu.mapapi.SDKInitializer;
import com.dq.android.travelcarrytreasure.R;

public class BaiDuMapActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_baidu_map);

    init();
  }

  private void init() {
    SDKInitializer.initialize(this);
  }
}
