package com.dq.android.travelcarrytreasure.ui.local;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationResponse;
import support.ui.adapters.EasyViewHolder;

/**
 * 作者：Create on 2017/5/2 22:48  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class LocalViewHolder extends EasyViewHolder<LocationResponse.DataBean.ModListBean.ListBean> {

  private final ImageView mImg;
  private TextView mTvType;
  private TextView mTvTitle;
  private RatingBar mRate;
  private TextView mTvPrice;
  private TextView mTvDistance;

  public LocalViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_location_nearby);
    mImg = (ImageView) itemView.findViewById(R.id.img_nearby);
    mTvType = (TextView) itemView.findViewById(R.id.tv_type);
    mTvTitle = (TextView) itemView.findViewById(R.id.tv_nearby_title);
    mRate = (RatingBar) itemView.findViewById(R.id.rate_nearby);
    mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
    mTvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
  }

  @Override public void bindTo(int position, LocationResponse.DataBean.ModListBean.ListBean values) {
    String type = (values.getType() == 1) ? "美食" : "景点";
    mTvType.setText(type);
    String name = (values.getType() == 1) ? values.getPoi().getName() : values.getPoi().getSname();
    mTvTitle.setText(name);
    String rate = (values.getType() == 1) ? values.getPoi().getOverall_rating() : values.getPoi().getAvg_remark_score();
    mRate.setRating(Float.parseFloat(rate));
    String price = String.valueOf((values.getPoi().getPrice().equals("0")) ? "免费开放" : "人均消费：¥" + values.getPoi().getPrice());
    mTvPrice.setText(price);
    String distance =
        (values.getPoi().getDistance() > 1000) ? (values.getPoi().getDistance() / 1000) + "km" : values.getPoi().getDistance() + "m";
    mTvDistance.setText(distance);
    // 图片
    Glide.with(itemView.getContext())
        .load(values.getPoi().getPic_url())
        .placeholder(R.drawable.bg_default_place_holder)
        .thumbnail(0.1f)
        .into(mImg);
  }
}
