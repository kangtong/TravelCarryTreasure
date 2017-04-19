package com.dq.android.travelcarrytreasure.ui.local;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import com.dq.android.travelcarrytreasure.R;
import com.dq.android.travelcarrytreasure.model.baidulvyou.HotCityResponse;
import support.ui.adapters.EasyViewHolder;

/**
 * 作者：Create on 2017/4/19 23:18  by dq_dana
 * 邮箱：dqdanavera@gmail.com
 * 描述：
 */
public class HotCityViewHolder extends EasyViewHolder<HotCityResponse.DataBean.ListBean> {

  private TextView mTvCity;

  public HotCityViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.item_hot_city);
    mTvCity = (TextView) itemView.findViewById(R.id.tv_city);
  }

  @Override public void bindTo(int i, HotCityResponse.DataBean.ListBean values) {
    mTvCity.setText(values.getSname());
  }
}
