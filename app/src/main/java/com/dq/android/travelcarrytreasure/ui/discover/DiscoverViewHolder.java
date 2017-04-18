package com.dq.android.travelcarrytreasure.ui.discover;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.DiscoverResponse;
import com.dq.android.travelcarrytreasure.util.TimeUtils;
import support.ui.adapters.EasyViewHolder;

/**
 * Created by DQDana on 2017/4/14
 */

public class DiscoverViewHolder extends EasyViewHolder<DiscoverResponse.DataBean.ModListBean.ListBean> {

  private final ImageView mImg;
  private TextView mTvTitle;
  private TextView mTvLookNum;
  private TextView mTvTime;

  public DiscoverViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_travels_note);
    ButterKnife.bind(this, itemView);
    mImg = (ImageView) itemView.findViewById(R.id.img);
    mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    mTvLookNum = (TextView) itemView.findViewById(R.id.tv_look_num);
    mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
  }

  @Override public void bindTo(int position, DiscoverResponse.DataBean.ModListBean.ListBean values) {
    Glide.with(itemView.getContext())
        .load(values.getExt().getPic_url())
        .thumbnail(0.1f)
        .into(mImg);
    mTvTitle.setText(values.getExt().getTitle());
    mTvLookNum.setText(String.valueOf(values.getExt().getView_count()));
    mTvTime.setText(TimeUtils.getDateFromTime(Long.parseLong(values.getExt().getStart_time()), "yyyy-MM-dd"));
  }
}
