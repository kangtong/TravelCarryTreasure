package com.dq.android.travelcarrytreasure.ui.local;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.LocationNowResponse;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import support.ui.adapters.EasyViewHolder;

/**
 * 作者：Create on 2017/5/3 12:01  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：当地 此刻 的 live 展示
 */
public class LocalLiveViewHolder extends EasyViewHolder<LocationNowResponse.DataBean.ListBean> {

  private final ImageView mImgBanner;
  private final ImageView mImgAvatar;
  private TextView mTvDesc;
  private TextView mTvPlace;

  public LocalLiveViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_location_live);
    mImgBanner = (ImageView) itemView.findViewById(R.id.img_banner);
    mImgAvatar = (ImageView) itemView.findViewById(R.id.img_avatar);
    mTvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
    mTvPlace = (TextView) itemView.findViewById(R.id.tv_place);
  }

  @Override public void bindTo(int position, LocationNowResponse.DataBean.ListBean values) {
    mTvDesc.setText(values.getDesc());
    mTvPlace.setText(values.getSname());
    // 图片
    Glide.with(itemView.getContext())
        .load(values.getPics().get(0).getPic_url())
        .placeholder(R.drawable.bg_default_place_holder)
        .thumbnail(0.1f)
        .into(mImgBanner);
    Glide.with(itemView.getContext())
        .load(values.getUser().getAvatar_pic())
        .bitmapTransform(new CropCircleTransformation(itemView.getContext()))
        .placeholder(R.drawable.bg_default_place_holder)
        .thumbnail(0.1f)
        .into(mImgAvatar);
  }
}
