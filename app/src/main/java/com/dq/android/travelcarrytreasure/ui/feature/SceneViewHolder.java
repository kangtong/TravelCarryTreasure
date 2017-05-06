package com.dq.android.travelcarrytreasure.ui.feature;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.SceneResponse;
import support.ui.adapters.EasyViewHolder;

/**
 * 作者：Create on 2017/5/6 12:28  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class SceneViewHolder extends EasyViewHolder<SceneResponse.DataBean.SceneListBean> {

  private final ImageView mImg;
  private TextView mTvPosition;
  private TextView mTvTitle;
  private RatingBar mRate;
  private TextView mTvRemark;
  private TextView mTvDesc;
  private ImageView mImgTickets;
  private TextView mTvPrice;
  private TextView mTvPriceOld;
  private TextView mTvDistance;

  public SceneViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_location_scene);
    mImg = (ImageView) itemView.findViewById(R.id.img);
    mTvPosition = (TextView) itemView.findViewById(R.id.tv_position);
    mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
    mRate = (RatingBar) itemView.findViewById(R.id.rate);
    mTvRemark = (TextView) itemView.findViewById(R.id.tv_remark);
    mTvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
    mImgTickets = (ImageView) itemView.findViewById(R.id.img_tickets);
    mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
    mTvPriceOld = (TextView) itemView.findViewById(R.id.tv_price_old);
    mTvDistance = (TextView) itemView.findViewById(R.id.tv_distance);
  }

  @Override public void bindTo(int i, SceneResponse.DataBean.SceneListBean values) {
    // 文字
    if (i <= 2) {
      mTvPosition.setText(String.valueOf(i + 1));
      mTvPosition.setVisibility(View.VISIBLE);
    } else {
      mTvPosition.setVisibility(View.GONE);
    }
    mTvTitle.setText(values.getSname());
    mRate.setRating(values.getAvg_remark_score());
    mTvDesc.setText(values.getDesc());
    mTvRemark.setText("(" + values.getRemark_count() + ")");
    if (values.getOta() == 1) {
      if (values.getPrice().equals(values.getOrigin_price())) {
        mTvPriceOld.setVisibility(View.GONE);
      } else {
        mTvPriceOld.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰
        mTvPriceOld.setText(String.format("¥%s", values.getPrice()));
      }
      mTvPrice.setText(String.format("¥%s", values.getOrigin_price()));
    } else {
      mImgTickets.setVisibility(View.GONE);
      mTvPrice.setVisibility(View.GONE);
      mTvPriceOld.setVisibility(View.GONE);
    }
    String distance = new java.text.DecimalFormat("#.#").format(values.getDistance() / 1000f) + "km";
    mTvDistance.setText(distance);
    // 图片
    Glide.with(itemView.getContext())
        .load(values.getPic_url())
        .placeholder(R.drawable.bg_default_place_holder)
        .thumbnail(0.1f)
        .centerCrop()
        .into(mImg);
  }
}
