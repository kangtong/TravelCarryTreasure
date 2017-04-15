package com.dq.android.travelcarrytreasure.ui.discover;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import support.ui.adapters.EasyViewHolder;

/**
 * Created by DQDana on 2017/4/14
 */

public class DiscoverViewHolder extends EasyViewHolder<DiscoverResponse> {

  @BindView(R.id.img) ImageView mImg;
  @BindView(R.id.tv_title) TextView mTvTitle;
  @BindView(R.id.tv_look_num) TextView mTvLookNum;
  @BindView(R.id.tv_time) TextView mTvTime;

  public DiscoverViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_travels_note);
  }

  @Override public void bindTo(int position, DiscoverResponse value) {
  }
}
